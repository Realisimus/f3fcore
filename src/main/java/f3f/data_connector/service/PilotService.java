package f3f.data_connector.service;

import f3f.data_connector.entity.Pilot;

import java.util.List;

public interface PilotService {

    Pilot getById(Long id);
    List<Pilot> getAll();
    void removeById(Long id);
    void save(Pilot pilot);

}
