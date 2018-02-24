package f3f.core;

import f3f.entity.Pilot;
import f3f.repository.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    PilotService pilotService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public void savePilot(String login, String first_name, String last_name, String license, String email) {
        Pilot pilot = new Pilot(login, first_name, last_name, license, email);
        pilotService.save(pilot);
    }

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public @ResponseBody List<Pilot> getAllPilots() {

       List<Pilot> pilots = pilotService.getAll();

        return pilots;
    }

}
