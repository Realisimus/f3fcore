package f3f.data_connector.service.impl;

import f3f.data_connector.entity.Cup;
import f3f.data_connector.repository.CupRepository;
import f3f.data_connector.service.CupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CupServiceImpl implements CupService {

    @Autowired
    protected CupRepository cupRepository;

    @Override
    public Cup getById(Long id) {
        return cupRepository.findOne(id);
    }

    @Override
    public List<Cup> getAll() {
        return (List<Cup>) cupRepository.findAll();
    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public void save(Cup cup) {

    }
}
