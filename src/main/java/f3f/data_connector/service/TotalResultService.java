package f3f.data_connector.service;

import f3f.data_connector.entity.Cup;
import f3f.data_connector.entity.Pilot;
import f3f.data_connector.entity.TotalResult;

import java.util.List;

public interface TotalResultService {

    List<TotalResult> getByCup(Cup cup);
    List<Pilot> getPilotsByCup(Cup cup);
    Integer getNumberOfPilotsByCup(Cup cup);
    void save(TotalResult totalResult);
    void saveAll(List<TotalResult> totalResults);

}
