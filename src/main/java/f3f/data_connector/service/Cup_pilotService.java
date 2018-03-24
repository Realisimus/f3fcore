package f3f.data_connector.service;

import f3f.data_connector.entity.Cup_pilot;

import java.util.List;

public interface Cup_pilotService {

    List<Cup_pilot> getAll();
    void removeById(Long id);
    void save(Cup_pilot cup_pilot);

}
