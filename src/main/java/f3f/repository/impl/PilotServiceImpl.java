package f3f.repository.impl;

import f3f.entity.Pilot;
import f3f.repository.PilotRepository;
import f3f.repository.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PilotServiceImpl implements PilotService {

    @Autowired
    protected PilotRepository pilotRepository;

    @Override
    public Pilot getById(Long id) {
        return pilotRepository.findOne(id);
    }

    @Override
    public List<Pilot> getAll() {
        return (List<Pilot>) pilotRepository.findAll();
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
