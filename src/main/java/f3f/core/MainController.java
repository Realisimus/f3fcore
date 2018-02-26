package f3f.core;

import f3f.data_connector.entity.Cup;
import f3f.data_connector.entity.Pilot;
import f3f.data_connector.service.CupService;
import f3f.data_connector.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    PilotService pilotService;

    @Autowired
    CupService cupService;

    @RequestMapping(value = "/put", method = RequestMethod.GET)
    public void savePilot(String login, String first_name, String last_name, String license, String email) {
        Pilot pilot = new Pilot(login, first_name, last_name, license, email);
        pilotService.save(pilot);
    }

    @RequestMapping(path = "/pilots.json", method = RequestMethod.GET)
    public @ResponseBody List<Pilot> getAllPilots() {
        return pilotService.getAll();
    }

    @RequestMapping(path = "/get_pilots/{id}", method = RequestMethod.GET)
    public @ResponseBody Pilot getPilotById(@PathVariable Long id) {
        return pilotService.getById(id);
    }


    @RequestMapping(path = "/get_cups", method = RequestMethod.GET)
    public @ResponseBody List<Cup> getAllCups() {
        return cupService.getAll();
    }

    @RequestMapping(path = "/cups", method = RequestMethod.GET)
    public ModelAndView getCupsById() {
        return new ModelAndView("new_table.html");
    }

    @RequestMapping(path = "/cup", method = RequestMethod.GET)
    public ModelAndView cup() {

        return new ModelAndView("index.html");
    }



}
