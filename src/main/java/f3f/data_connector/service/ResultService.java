package f3f.data_connector.service;

import f3f.data_connector.entity.Result;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.List;

public interface ResultService {

    Result getById(Long id);
    List<Result> getByCupId(Long cup_id);
    List<Result> getByCupIdAndRound(Long cup_id, Integer round);
    List<Result> getByCupIdAndPilotId(Long cup_id, Long pilot_id);
    List<Result> getByCupIdAndPilotIdAndRound(Long cup_id, Long pilot_id, Integer round);
    void save(Result result);
    void saveAll(List<Result> results);
    void removeById(Long id);

}
