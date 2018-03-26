package f3f.core;

import f3f.data_connector.entity.Pilot;
import f3f.data_connector.service.Cup_pilotService;
import f3f.data_connector.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity savePilot(@RequestParam String login,
                                    String first_name,
                                    String last_name,
                                    String license,
                                    String email,
                                    String phone,
                                    String city ) {
        Pilot pilot = pilotService.getByLogin(login);
        if (pilot != null) {
            pilot.setLogin(login);
            pilot.setFirst_name(first_name);
            pilot.setLast_name(last_name);
            pilot.setLicense(license);
            pilot.setEmail(email);
            pilot.setPhone(phone);
            pilot.setCity(city);
            pilotService.save(pilot);
        } else {
            Pilot newPilot = new Pilot(login, first_name, last_name, license, email, phone, city);
            pilotService.save(newPilot);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(path = "/pilots", method = RequestMethod.GET)
    public @ResponseBody List<Pilot> getAllPilots() {
        return pilotService.getAll();
    }

    @RequestMapping(path = "/pilot/{pilot_id}", method = RequestMethod.GET)
    public @ResponseBody Pilot getPilotById(@PathVariable Long pilot_id) {
        return pilotService.getById(pilot_id);
    }

    @RequestMapping(path = "/pilots/{cup_id}", method = RequestMethod.GET)
    public @ResponseBody List<Pilot> getPilotsByCupId(@PathVariable Long cup_id) {
        return pilotService.getPilotsByCupId(cup_id);
    }
}
