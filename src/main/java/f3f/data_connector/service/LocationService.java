package f3f.data_connector.service;

import f3f.data_connector.entity.Location;

import java.util.List;

public interface LocationService {

    Location getById(Integer id);
    List<Location> getAll();
    void removeById(Integer id);
    void save(Location location);

}
