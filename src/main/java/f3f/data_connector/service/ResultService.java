package f3f.data_connector.service;

import f3f.data_connector.entity.Result;

import java.util.List;

public interface ResultService {

    Result getById(Long id);
    List<Result> getByCupId(Long cup_id);
    List<Result> getByCupIdAndRound(Long cup_id, Integer round);
    List<Float> getTimesByCupIdAndRound(Long cup_id, Integer round);
    List<Result> getByCupIdAndPilotIdAndRound(Long cup_id, Long pilot_id, Integer round);
    void save(Result result);
    void removeById(Long id);

}
