package f3f.data_connector.service;

import f3f.data_connector.entity.Cup;
import f3f.data_connector.entity.Pilot;
import f3f.data_connector.entity.Result;

import java.util.List;

public interface ResultService {

    List<Result> getByCup(Cup cup);
    List<Result> getByCupAndRound(Cup cup, Integer round);
    List<Result> getByCupAndPilot(Cup cup, Pilot pilot);
    Result getByCupAndPilotAndRound(Cup cup, Pilot pilot, Integer round);
    void save(Result result);
    void saveAll(List<Result> results);

}
