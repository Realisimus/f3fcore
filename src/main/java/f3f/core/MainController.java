package f3f.core;

import f3f.data_connector.entity.Cup;
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


    @RequestMapping(path = "/cups", method = RequestMethod.GET)
    public List<Cup> getAllCups() {
        return cupService.getAll();
    }

    @RequestMapping(path = "/cup/{cup_id}", method = RequestMethod.GET)
    public Cup getCupById(@PathVariable Long cup_id) {
        return cupService.getById(cup_id);
    }

    @RequestMapping(path = "/cup/{cup_id}/rounds", method = RequestMethod.GET)
    public List<Result> getRoundsByCupId(@PathVariable Long cup_id) {
        return resultService.getByCupId(cup_id);
    }

}
