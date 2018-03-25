package f3f.core;

import f3f.data_connector.entity.Cup;
import f3f.data_connector.entity.Cup_pilot;
import f3f.data_connector.entity.Pilot;
import f3f.data_connector.entity.Result;
import f3f.data_connector.service.CupService;
import f3f.data_connector.service.Cup_pilotService;
import f3f.data_connector.service.PilotService;
import f3f.data_connector.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    protected PilotService pilotService;

    @Autowired
    protected CupService cupService;

    @Autowired
    protected ResultService resultService;
    
    @Autowired
    protected Cup_pilotService cup_pilotService;

    @RequestMapping(value = "/add_pilot", method = RequestMethod.POST)
    public void savePilot(String login, String first_name, String last_name, String license, String email) {
        Pilot pilot = new Pilot(login, first_name, last_name, license, email);
        pilotService.save(pilot);
    }

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
        resultRecalculate(cup_id, round);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(path = "/pilots", method = RequestMethod.GET)
    public @ResponseBody List<Pilot> getAllPilots() {
        return pilotService.getAll();
    }

    @RequestMapping(path = "/pilot/{id}", method = RequestMethod.GET)
    public @ResponseBody Pilot getPilotById(@PathVariable Long id) {
        return pilotService.getById(id);
    }


    @RequestMapping(path = "/cups", method = RequestMethod.GET)
    public @ResponseBody List<Cup> getAllCups() {
        return cupService.getAll();
    }

    @RequestMapping(path = "/cup/{cup_id}", method = RequestMethod.GET)
    public @ResponseBody Cup getCupById(@PathVariable Long cup_id) {
        return cupService.getById(cup_id);
    }

    @RequestMapping(path = "/cup/{cup_id}/rounds", method = RequestMethod.GET)
    public @ResponseBody List<Result> getRoundsByCupId(@PathVariable Long cup_id) {
        return resultService.getByCupId(cup_id);
    }

    @RequestMapping(path = "/cup/{cup_id}/pilots", method = RequestMethod.GET)
    public @ResponseBody List<Pilot> getPilotsByCupId(@PathVariable Long cup_id) {
        return pilotService.getPilotsByCupId(cup_id);
    }

    private void resultRecalculate(Long cup_id, Integer round) {
        List<Result> results = resultService.getByCupIdAndRound(cup_id, round);
        if (results.size() == pilotService.getPilotsByCupId(cup_id).size()) {
            Float minTime = minTime(results);
            for (Result r : results) {
                r.setScore(1000 * minTime / r.getTime());
            }
            resultService.saveAll(results);
            updateTotalScore(cup_id);
        }
    }

    private void updateTotalScore(Long cup_id) {
        List<Cup_pilot> total = cup_pilotService.getByCupId(cup_id);
        Float minScore1 = null;
        Float minScore2 = null;
        int i = 0;
        for (Cup_pilot cp : total) {
            List<Result> results = resultService.getByCupIdAndPilotId(cup_id, cp.getPilot_id());
            minScore1 = 1000f;
            minScore2 = 1000f;
            cp.setScore((float) 0);
            for (Result r : results) {
                cp.setScore(cp.getScore() + r.getScore());
                if (r.getScore() < minScore1) {
                    minScore1 = r.getScore();
                }
                if (r.getScore() < minScore2 && r.getScore() > minScore1) {
                    minScore2 = r.getScore();
                }
            }
            if (results.size() < 15) {
                cp.setScore(cp.getScore() - minScore1);
            } else {
                cp.setScore(cp.getScore() - minScore1 - minScore2);
            }
        }
        cup_pilotService.saveAll(total);
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
