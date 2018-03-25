package f3f.data_connector.service.impl;

import f3f.data_connector.entity.Pilot;
import f3f.data_connector.repository.Cup_pilotRepository;
import f3f.data_connector.repository.PilotRepository;
import f3f.data_connector.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PilotServiceImpl implements PilotService {

    @Autowired
    protected PilotRepository pilotRepository;

    @Autowired
    protected Cup_pilotRepository cup_pilotRepository;

    @Override
    public Pilot getById(Long id) {
        return pilotRepository.findOne(id);
    }

    @Override
    public List<Pilot> getAll() {
        return (List<Pilot>) pilotRepository.findAll();
    }

    @Override
    public List<Pilot> getPilotsByCupId(Long cup_id) {
        List<Long> pilotsId = cup_pilotRepository.getPilotIdByCup_id(cup_id);
        return (List<Pilot>) pilotRepository.findAll(pilotsId);
    }

    @Override
    public void removeById(Long id) {
        pilotRepository.delete(id);
    }

    @Override
    public void save(Pilot pilot) {
        pilotRepository.save(pilot);
    }
}
