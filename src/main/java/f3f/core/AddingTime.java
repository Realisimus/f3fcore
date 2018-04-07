package f3f.core;

import f3f.data_connector.entity.Cup;
import f3f.data_connector.entity.Pilot;
import f3f.data_connector.entity.Result;
import f3f.data_connector.entity.TotalResult;
import f3f.data_connector.service.CupService;
import f3f.data_connector.service.PilotService;
import f3f.data_connector.service.ResultService;
import f3f.data_connector.service.TotalResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
public class AddingTime {

    private Parameters parameters = new Parameters();

    @Autowired
    protected PilotService pilotService;

    @Autowired
    protected CupService cupService;

    @Autowired
    protected ResultService resultService;

    @Autowired
    protected TotalResultService totalResultService;

    @RequestMapping(value = "/add_time", method = RequestMethod.POST)
    public ResponseEntity addTime(@RequestParam String pilot_id_s,
                                  @RequestParam String cup_id_s,
                                  @RequestParam String round_s,
                                  @RequestParam String time_s,
                                  @RequestParam String penalty_s) {
        add(pilot_id_s,cup_id_s,round_s,time_s,penalty_s);
        return new ResponseEntity(HttpStatus.OK);
    }


    private void add(String pilot_id_s,
                     String cup_id_s,
                     String round_s,
                     String time_s,
                     String penalty_s) {
        Pilot pilot = pilotService.getById(Long.parseLong(pilot_id_s));
        Cup cup = cupService.getById(Long.parseLong(cup_id_s));
        Integer round = Integer.parseInt(round_s);
        Integer time = round(Float.parseFloat(time_s) * parameters.getTimeRound());
        Integer penalty = Integer.parseInt(penalty_s);
        Result result = resultService.getByCupAndPilotAndRound(cup, pilot, round);
        if (result != null) {
            result.setTime(time);
            result.setPenalty(penalty);
            resultService.save(result);
        } else {
            Result resultNew = new Result(pilot, cup, round, time, penalty);
            resultService.save(resultNew);
        }
        updateRoundScore(cup, round);
    }

    private void updateRoundScore(Cup cup, Integer round) {
        List<Result> results = resultService.getByCupAndRound(cup, round);
        List<Pilot> pilots = totalResultService.getPilotsByCup(cup);

        if (results.size() == pilots.size()) {
            results = updateResultsScore(results);
            results = updateResultsPercentages(results);
            resultService.saveAll(results);
            updateTotalScore(cup);
        }
    }

    private void updateTotalScore(Cup cup) {
        List<TotalResult> totalResults = totalResultService.getByCup(cup);

        totalResults = updateCup_pilotsScoreAndRawScore(totalResults, cup);
        totalResults = updateCup_pilotsRank(totalResults);
        totalResults = updateCup_pilotsPercentages(totalResults);
        totalResultService.saveAll(totalResults);
    }

    private List<TotalResult> updateCup_pilotsScoreAndRawScore(List<TotalResult> totalResults, Cup cup) {
        Integer minScore1;
        Integer minScore2;
        for (TotalResult cp : totalResults) {
            List<Result> resultsForPilot = resultService.getByCupAndPilot(cup, cp.getPilot());
            minScore1 = 1000*parameters.getScoreRound();
            minScore2 = 1000*parameters.getScoreRound();
            cp.setScore(0);
            cp.setRaw_score(0);
            for (Result r : resultsForPilot) {
                cp.setScore(cp.getScore() + r.getScore());
                cp.setRaw_score(cp.getRaw_score() + r.getScore());
                if (r.getScore() < minScore1) {
                    minScore2 = minScore1;
                    minScore1 = r.getScore();
                } else {
                    if (r.getScore() < minScore2) {
                        minScore2 = r.getScore();
                    }
                }
            }
                if (resultsForPilot.size() > 4) {
                    cp.setScore(cp.getScore() - minScore1);
                }
                if (resultsForPilot.size() > 14) {
                    cp.setScore(cp.getScore() - minScore2);
                }
        }
            return totalResults;
        }

        private List<TotalResult> updateCup_pilotsRank (List <TotalResult> total) {
            Collections.sort(total);
            for (int i = 0; i < total.size(); i++) {
                total.get(i).setRank(i + 1);
                total.get(i).setPercents(100 * total.get(i).getScore() / total.get(0).getScore());
            }
            return total;
        }

        private List<TotalResult> updateCup_pilotsPercentages (List <TotalResult> total) {
            Collections.sort(total);
            for (int i = 0; i < total.size(); i++) {
                total.get(i).setRank(i + 1);
                total.get(i).setPercents(100 * total.get(i).getScore() / total.get(0).getScore());
            }
            return total;
        }

        private List<Result> updateResultsScore (List < Result > results) {
            Integer minTime = minTime(results);
            for (Result r : results) {
                if (r.getTime() > 0) {
                    r.setScore(round(1000 * parameters.getScoreRound() * minTime / r.getTime()));
                } else {
                    r.setScore(0);
                }
            }
            return results;
        }

        private List<Result> updateResultsPercentages (List < Result > results) {
            Collections.sort(results);
            for (Result r : results) {
                r.setPercentages(100 * r.getScore() / results.get(0).getScore());
            }
            return results;
        }

        private Integer minTime (List<Result> results) {
            Integer minTime = 999999999;
            for (Result result : results) {
                if (result.getTime() < minTime && result.getTime() > 0) {
                    minTime = result.getTime();
                }
            }
            return minTime;
        }

        private static Integer round (float number){
            int pow = 100;
            float tmp = number * pow;
            return (int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp) / pow;
        }

    }
