package f3f.core;

import f3f.data_connector.entity.Pilot;
import f3f.data_connector.service.Cup_pilotService;
import f3f.data_connector.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PilotController {

    @Autowired
    protected PilotService pilotService;

    @Autowired
    protected Cup_pilotService cup_pilotService;

    @RequestMapping(value = "/add_pilot", method = RequestMethod.POST)
    public void savePilot(@RequestParam String login,
                          String first_name,
                          String last_name,
                          String license,
                          String email,
                          String phone,
                          String city ) {
        Pilot pilot = new Pilot(login, first_name, last_name, license, email, phone, city);
        pilotService.save(pilot);
    }

    @RequestMapping(path = "/pilots", method = RequestMethod.GET)
    public List<Pilot> getAllPilots() {
        return pilotService.getAll();
    }

    @RequestMapping(path = "/pilot/{pilot_id}", method = RequestMethod.GET)
    public Pilot getPilotById(@PathVariable Long pilot_id) {
        return pilotService.getById(pilot_id);
    }

    @RequestMapping(path = "/pilots/{cup_id}", method = RequestMethod.GET)
    public List<Pilot> getPilotsByCupId(@PathVariable Long cup_id) {
        return pilotService.getPilotsByCupId(cup_id);
    }
}
