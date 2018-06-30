package f3f.core;

import f3f.core.model.AddingTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Controller
public class ResultController {

    private final AddingTime addingTime;

    @Autowired
    public ResultController(AddingTime addingTime) {
        this.addingTime = addingTime;
    }

    @RequestMapping(value = "/add_time", method = RequestMethod.POST)
    public ResponseEntity addTime(Integer id,
                                  @RequestParam Integer pilot_id,
                                  @RequestParam Integer cup_id,
                                  @RequestParam Integer round,
                                  @RequestParam BigDecimal time,
                                  Integer penalty) {
        addingTime.add(id,pilot_id,cup_id,round,time,penalty);
        return new ResponseEntity(HttpStatus.OK);
    }

}
