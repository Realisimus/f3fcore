package f3f.data_connector.service;

import f3f.data_connector.entity.Result;

import java.util.List;

public interface ResultService {

    Result getById(Long id);
    List<Result> getByCupId(Long cup_id);
    List<Result> getByCupIdAndRound(Long cup_id, Long round);
    void save(Result result);
    void removeById(Long id);

}
