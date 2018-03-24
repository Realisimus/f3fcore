package f3f.data_connector.service;

import f3f.data_connector.entity.Cup_pilot;
import f3f.data_connector.entity.Pilot;

import java.util.List;

public interface Cup_pilotService {

    List<Cup_pilot> getAll();
    List<Pilot> getPilotsByCupId(Long cup_id);
    void removeById(Long id);
    void save(Cup_pilot cup_pilot);

}
