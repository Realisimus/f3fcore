package f3f.data_connector.service;

import f3f.data_connector.entity.Cup;

import java.util.List;

public interface CupService {

    Cup getById(Long id);
    List<Cup> getAll();
    void removeById(Long id);
    void save(Cup cup);

}
