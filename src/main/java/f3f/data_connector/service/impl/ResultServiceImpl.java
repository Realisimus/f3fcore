package f3f.data_connector.service.impl;

import f3f.data_connector.entity.Cup;
import f3f.data_connector.entity.Pilot;
import f3f.data_connector.entity.Result;
import f3f.data_connector.repository.ResultRepository;
import f3f.data_connector.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    protected ResultRepository resultRepository;

    @Override
    public List<Result> getByCup(Cup cup) {
        return resultRepository.findByCup(cup);
    }

    @Override
    public List<Result> getByCupAndRound(Cup cup, Integer round) {
        return resultRepository.findByCupAndRound(cup, round);
    }

    @Override
    public List<Result> getByCupAndPilot(Cup cup, Pilot pilot) {
        return resultRepository.findByCupAndPilot(cup, pilot);
    }

    @Override
    public Result getByCupAndPilotAndRound(Cup cup, Pilot pilot, Integer round) {
        return resultRepository.findByCupAndPilotAndRound(cup, pilot, round);
    }

    @Override
    public void save(Result result) {
        resultRepository.save(result);
    }

    @Override
    public void saveAll(List<Result> results) {
        resultRepository.save(results);
    }
}
