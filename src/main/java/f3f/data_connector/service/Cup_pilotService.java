package f3f.data_connector.service;

import f3f.data_connector.entity.Cup_pilot;

import java.util.List;

public interface Cup_pilotService {

    List<Cup_pilot> getAll();
    List<Cup_pilot> getByCupId(Long cup_id);
    List<Long> getPilotIdByCupId(Long cup_id);
    void removeById(Long id);
    void save(Cup_pilot cup_pilot);
    void saveAll(List<Cup_pilot> cup_pilots);

}
