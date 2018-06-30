package f3f.data_connector.service.impl;

import f3f.data_connector.entity.Cup;
import f3f.data_connector.entity.Pilot;
import f3f.data_connector.entity.TotalResult;
import f3f.data_connector.repository.TotalResultRepository;
import f3f.data_connector.service.TotalResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TotalResultServiceImpl implements TotalResultService {

    private final TotalResultRepository totalResultRepository;

    @Autowired
    public TotalResultServiceImpl(TotalResultRepository totalResultRepository) {
        this.totalResultRepository = totalResultRepository;
    }

    @Override
    public List<TotalResult> getByCup(Cup cup) {
        return totalResultRepository.findByCup(cup);
    }

    @Override
    public List<Pilot> getPilotsByCup(Cup cup) {
        return totalResultRepository.findPilotsByCup(cup);
    }

    @Override
    public Integer getNumberOfPilotsByCup(Cup cup) {
        return totalResultRepository.findNumberOfPilotsByCup(cup);
    }

    @Override
    public void save(TotalResult totalResult) {

    }

    @Override
    public void saveAll(List<TotalResult> totalResults) {
        totalResultRepository.save(totalResults);
    }
}
