package f3f.repository;

import f3f.entity.Pilot;

import java.util.List;

public interface PilotService {

    Pilot getById(Long id);
    List<Pilot> getAll();
    void removeById(Long id);
    void save(Pilot pilot);

}
