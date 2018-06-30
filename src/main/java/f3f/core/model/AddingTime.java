package f3f.core.model;

import f3f.core.model.tools.SortTotalResults;
import f3f.data_connector.entity.Cup;
import f3f.data_connector.entity.Pilot;
import f3f.data_connector.entity.Result;
import f3f.data_connector.entity.TotalResult;
import f3f.data_connector.service.CupService;
import f3f.data_connector.service.PilotService;
import f3f.data_connector.service.ResultService;
import f3f.data_connector.service.TotalResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Controller
public class AddingTime {

    private final PilotService pilotService;

    private final CupService cupService;

    private final ResultService resultService;

    private final TotalResultService totalResultService;

    @Autowired
    public AddingTime(PilotService pilotService, CupService cupService, ResultService resultService, TotalResultService totalResultService) {
        this.pilotService = pilotService;
        this.cupService = cupService;
        this.resultService = resultService;
        this.totalResultService = totalResultService;
    }

    public void add( Integer id,
                     Integer pilot_id,
                     Integer cup_id,
                     Integer round,
                     BigDecimal time,
                     Integer penalty) {
        Pilot pilot = pilotService.getById(pilot_id);
        Cup cup = cupService.getById(cup_id);
        time = time.setScale(2, RoundingMode.DOWN);
        Result result = new Result(id, pilot, cup, round, time, penalty);
        resultService.save(result);
        updateRoundScore(cup, round);
    }

    private void updateRoundScore(Cup cup, Integer round) {
        List<Result> results = resultService.getByCupAndRound(cup, round);
        Integer numberOfPilots = totalResultService.getNumberOfPilotsByCup(cup);
        if (results.size() == numberOfPilots) {
            results = updateResultsScore(results);
            results = updateResultsPercentages(results);
            resultService.saveAll(results);
            updateTotalScore(cup);
        }
    }

    private List<Result> updateResultsScore (List < Result > results) {
        BigDecimal minTime = minTime(results);
        for (Result r : results) {
            if (r.getTime().compareTo(BigDecimal.ZERO) > 0) {
                r.setScore(minTime.multiply(new BigDecimal(1000)).divide(r.getTime(), 2, RoundingMode.HALF_UP));
            } else {
                r.setScore(BigDecimal.ZERO);
            }
        }
        return results;
    }

    private List<Result> updateResultsPercentages (List < Result > results) {
        for (Result r : results) {
            r.setPercentages(r.getScore().divide(new BigDecimal(10), 2, RoundingMode.HALF_UP));
        }
        return results;
    }

    private void updateTotalScore(Cup cup) {
        List<TotalResult> totalResults = totalResultService.getByCup(cup);

        totalResults = updateCup_pilotsScoreAndRawScore(totalResults, cup);
        totalResults = updateCup_pilotsRank_and_pilotPercents(totalResults);
        totalResultService.saveAll(totalResults);
    }

    private List<TotalResult> updateCup_pilotsScoreAndRawScore(List<TotalResult> totalResults, Cup cup) {
        BigDecimal minScore1;
        BigDecimal minScore2;
        for (TotalResult pilotTotalResult : totalResults) {
            List<Result> resultsForPilot = resultService.getByCupAndPilot(cup, pilotTotalResult.getPilot());
            minScore1 = new BigDecimal(1000);
            minScore2 = new BigDecimal(1000);
            pilotTotalResult.setScore(BigDecimal.ZERO);
            pilotTotalResult.setRaw_score(BigDecimal.ZERO);
            for (Result r : resultsForPilot) {
                pilotTotalResult.setScore(pilotTotalResult.getScore().add(r.getScore()));
                pilotTotalResult.setRaw_score(pilotTotalResult.getRaw_score().add(r.getScore()));
                if (minScore1.compareTo(r.getScore())>0) {
                    minScore2 = minScore1;
                    minScore1 = r.getScore();
                } else {
                    if (minScore2.compareTo(r.getScore())>0) {
                        minScore2 = r.getScore();
                    }
                }
            }
                if (resultsForPilot.size() > 4) {
                    pilotTotalResult.setScore(pilotTotalResult.getScore().subtract(minScore1));
                }
                if (resultsForPilot.size() > 14) {
                    pilotTotalResult.setScore(pilotTotalResult.getScore().subtract(minScore2));
                }
        }
            return totalResults;
        }

        private List<TotalResult> updateCup_pilotsRank_and_pilotPercents(List <TotalResult> totalResults) {
            totalResults.sort(SortTotalResults.SORT_BY_SCORE);
            for (int i = 0; i < totalResults.size(); i++) {
                TotalResult r = totalResults.get(i);
                r.setRank(i + 1);
                r.setPercents(r.getScore().multiply(new BigDecimal(100)).divide(totalResults.get(0).getScore(), 2, RoundingMode.HALF_UP));
            }
            return totalResults;
        }

        private BigDecimal minTime (List<Result> results) {
            BigDecimal minTime = new BigDecimal(999999999 );
            for (Result result : results) {
                if (result.getTime().compareTo(minTime)<0 && result.getTime().compareTo(BigDecimal.ZERO) > 0) {
                    minTime = result.getTime();
                }
            }
            return minTime;
        }

    }
