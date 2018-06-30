package f3f.core;

import f3f.core.model.AddingTime;
import f3f.data_connector.entity.Cup;
import f3f.data_connector.entity.Result;
import f3f.data_connector.entity.TotalResult;
import f3f.data_connector.service.CupService;
import f3f.data_connector.service.ResultService;
import f3f.data_connector.service.TotalResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CupController {

    private CupService cupService;

    private ResultService resultService;

    private TotalResultService totalResultService;

    @Autowired
    public CupController(CupService cupService, ResultService resultService, TotalResultService totalResultService) {
        this.cupService = cupService;
        this.resultService = resultService;
        this.totalResultService = totalResultService;
    }

    @RequestMapping(path = "/cups", method = RequestMethod.GET)
    public @ResponseBody List<Cup> getAllCups() {
        return cupService.getAll();
    }

    @RequestMapping(path = "/cup/{cup_id}", method = RequestMethod.GET)
    public @ResponseBody Cup getCupById(@PathVariable Integer cup_id) {
        return cupService.getById(cup_id);
    }

    @RequestMapping(path = "/cup/{cup_id}/rounds", method = RequestMethod.GET)
    public @ResponseBody List<Result> getRoundsByCupId(@PathVariable Integer cup_id) {
        return resultService.getByCup(cupService.getById(cup_id));
    }

    @RequestMapping(path = "/cup/{cup_id}/total", method = RequestMethod.GET)
    public @ResponseBody List<TotalResult> getTotalByCupId(@PathVariable Integer cup_id) {
        return totalResultService.getByCup(cupService.getById(cup_id));
    }

}
