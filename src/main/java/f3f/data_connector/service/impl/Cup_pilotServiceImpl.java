package f3f.data_connector.service.impl;

import f3f.data_connector.entity.Cup_pilot;
import f3f.data_connector.repository.Cup_pilotRepository;
import f3f.data_connector.service.Cup_pilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Cup_pilotServiceImpl implements Cup_pilotService {

    @Autowired
    protected Cup_pilotRepository cup_pilotRepository;

    @Override
    public List<Cup_pilot> getAll() {
        return (List<Cup_pilot>) cup_pilotRepository.findAll();
    }

    @Override
    public List<Cup_pilot> getByCupId(Long cup_id) {
        return cup_pilotRepository.getByCup_id(cup_id);
    }

    @Override
    public List<Long> getPilotIdByCupId(Long cup_id) {
        return cup_pilotRepository.getPilotIdByCup_id(cup_id);
    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public void save(Cup_pilot cup_pilot) {

    }

    @Override
    public void saveAll(List<Cup_pilot> cup_pilots) {
        cup_pilotRepository.save(cup_pilots);
    }
}
