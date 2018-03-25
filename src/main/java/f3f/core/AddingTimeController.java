package f3f.core;

import f3f.data_connector.entity.Cup_pilot;
import f3f.data_connector.entity.Result;
import f3f.data_connector.service.Cup_pilotService;
import f3f.data_connector.service.PilotService;
import f3f.data_connector.service.ResultService;
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
public class AddingTimeController {

    @Autowired
    protected ResultService resultService;

    @Autowired
    protected PilotService pilotService;

    @Autowired
    protected Cup_pilotService cup_pilotService;

    @RequestMapping(value = "/add_time", method = RequestMethod.POST)
    public ResponseEntity addTimeAndRecalculate(@RequestParam String pilot_id_s,
                                                @RequestParam String cup_id_s,
                                                @RequestParam String round_s,
                                                @RequestParam String time_s,
                                                @RequestParam String penalty_s) {
        Long pilot_id = Long.parseLong(pilot_id_s);
        Long cup_id = Long.parseLong(cup_id_s);
        Integer round = Integer.parseInt(round_s);
        Float time = Float.parseFloat(time_s);
        Integer penalty = Integer.parseInt(penalty_s);
        Result result = new Result(pilot_id, cup_id, round, time, penalty);
        resultService.save(result);

        updateResultsScore(cup_id, round);

        return new ResponseEntity(HttpStatus.OK);

    }

    private void updateResultsScore(Long cup_id, Integer round) {
        List<Result> results = resultService.getByCupIdAndRound(cup_id, round);

        if (results.size() == pilotService.getPilotsByCupId(cup_id).size())
        {
            results = updateResultsScore(results);
            results = updateResultsPercentages(results);
            resultService.saveAll(results);
            updateTotalScore(cup_id);
        }
    }

    private void updateTotalScore(Long cup_id) {
        List<Cup_pilot> cup_pilots = cup_pilotService.getByCupId(cup_id);

        cup_pilots = updateCup_pilotsScoreAndRawScore(cup_pilots, cup_id);
        cup_pilots = updateCup_pilotsRank(cup_pilots);
        cup_pilots = updateCup_pilotsPercentages(cup_pilots);
        cup_pilotService.saveAll(cup_pilots);
    }

    private List<Cup_pilot> updateCup_pilotsScoreAndRawScore(List<Cup_pilot> cup_pilots, Long cup_id) {
        Float minScore1;
        Float minScore2;
        for (Cup_pilot cp : cup_pilots) {
            List<Result> resultsForPilot = resultService.getByCupIdAndPilotId(cup_id, cp.getPilot_id());
            minScore1 = 1000f;
            minScore2 = 1000f;
            cp.setScore(0f);
            cp.setRaw_score(0f);
            for (Result r : resultsForPilot) {
                cp.setScore(cp.getScore() + r.getScore());
                cp.setRaw_score(cp.getRaw_score() + r.getScore());
                if (r.getScore() < minScore1) {
                    minScore1 = r.getScore();
                }
                if (r.getScore() < minScore2 && r.getScore() > minScore1) {
                    minScore2 = r.getScore();
                }
            }
            if (resultsForPilot.size() < 15 && resultsForPilot.size() > 1) {
                cp.setScore(cp.getScore() - minScore1);
            }
            if (resultsForPilot.size() > 14) {
                cp.setScore(cp.getScore() - minScore2);
            }
        }
        return cup_pilots;
    }

    private List<Cup_pilot> updateCup_pilotsRank(List<Cup_pilot> total) {
        Collections.sort(total);
        for (int i = 0; i < total.size(); i++) {
            total.get(i).setRank(i+1);
            total.get(i).setPercents(100 * total.get(i).getScore() / total.get(0).getScore());
        }
        return total;
    }

    private List<Cup_pilot> updateCup_pilotsPercentages(List<Cup_pilot> total) {
        Collections.sort(total);
        for (int i = 0; i < total.size(); i++) {
            total.get(i).setRank(i+1);
            total.get(i).setPercents(100 * total.get(i).getScore() / total.get(0).getScore());
        }
        return total;
    }

    private List<Result> updateResultsScore(List<Result> results) {
        Float minTime = minTime(results);
        for (Result r : results)
        {
            r.setScore(1000 * minTime / r.getTime());
        }
        return results;
    }

    private List<Result> updateResultsPercentages(List<Result> results) {
        Collections.sort(results);
        for (Result r : results) {
            r.setPercentages(100 * r.getScore() / results.get(0).getScore());
        }
        return results;
    }

    private Float minTime(List<Result> results) {
        Float minTime = results.get(0).getTime();
        for (Result result : results) {
            if (result.getTime() < minTime) {
                minTime = result.getTime();
            }
        }
        return minTime;
    }

}
