package f3f.data_connector.service;

import f3f.data_connector.entity.Pilot;

import java.util.List;

public interface PilotService {

    Pilot getById(Long id);
    Pilot getByLogin(String login);
    List<Pilot> getAll();
    List<Pilot> getPilotsByCupId(Long cup_id);
    void removeById(Long id);
    void save(Pilot pilot);

}
