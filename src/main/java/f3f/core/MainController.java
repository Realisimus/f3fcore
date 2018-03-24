package f3f.core;

import f3f.core.tools.ResultTools;
import f3f.data_connector.entity.Cup;
import f3f.data_connector.entity.Cup_pilot;
import f3f.data_connector.entity.Pilot;
import f3f.data_connector.entity.Result;
import f3f.data_connector.service.CupService;
import f3f.data_connector.service.Cup_pilotService;
import f3f.data_connector.service.PilotService;
import f3f.data_connector.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    PilotService pilotService;

    @Autowired
    CupService cupService;

    @Autowired
    Cup_pilotService cup_pilotService;

    @Autowired
    ResultService resultService;

    private ResultTools resultTools;

    @RequestMapping(value = "/add_pilot", method = RequestMethod.POST)
    public void savePilot(String login, String first_name, String last_name, String license, String email) {
        Pilot pilot = new Pilot(login, first_name, last_name, license, email);
        pilotService.save(pilot);
    }

    @RequestMapping(value = "/add_time", method = RequestMethod.POST)
    public void addTimeAndRecalculate(Long pilot_id, Long cup_id, Long round, Float time, Integer penalty) {
        Result result = new Result(pilot_id, cup_id, round, time, penalty);
        resultService.save(result);
        resultRecalculate(cup_id, round);
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

    private void resultRecalculate(Long cup_id, Long round_id) {
        List<Result> results = resultService.getByCupIdAndRound(cup_id, round_id);
        List<Float> times = resultTools.getTimes(results);
        if (times.size() == cup_pilotService.getAll().size()) {
            for (Result result : results) {
                result.setScore(1000 * resultTools.minTime(times) / result.getTime());
            }
        }
    }

}
