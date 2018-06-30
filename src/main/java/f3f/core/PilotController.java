package f3f.core;

import f3f.data_connector.entity.Pilot;
import f3f.data_connector.service.CupService;
import f3f.data_connector.service.TotalResultService;
import f3f.data_connector.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PilotController {

    private final PilotService pilotService;

    private final TotalResultService totalResultService;

    private final CupService cupService;

    @Autowired
    public PilotController(PilotService pilotService, TotalResultService totalResultService, CupService cupService) {
        this.pilotService = pilotService;
        this.totalResultService = totalResultService;
        this.cupService = cupService;
    }

    @RequestMapping(value = "/add_pilot", method = RequestMethod.POST)
    public ResponseEntity savePilot(Integer id,
                                    String login,
                                    String first_name,
                                    String last_name,
                                    String license,
                                    String email,
                                    String phone,
                                    String city ) {
        Pilot pilot = new Pilot(id, login, first_name, last_name, license, email, phone, city);
        pilotService.save(pilot);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(path = "/pilots", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Pilot> getAllPilots() {
        return pilotService.getAll();
    }

    @RequestMapping(path = "/pilot/{pilot_id}", method = RequestMethod.GET)
    @ResponseBody
    public Pilot getPilotById(@PathVariable Integer pilot_id) {
        return pilotService.getById(pilot_id);
    }

    @RequestMapping(path = "/pilots/{cup_id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Pilot> getPilotsByCupId(@PathVariable Integer cup_id) {
        return totalResultService.getPilotsByCup(cupService.getById(cup_id));
    }
}
