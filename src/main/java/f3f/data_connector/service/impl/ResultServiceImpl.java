package f3f.data_connector.service.impl;

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
    public Result getById(Long id) {
        return resultRepository.findOne(id);
    }

    @Override
    public List<Result> getByCupId(Long cup_id) {
        return resultRepository.findResultsByCup_id(cup_id);
    }

    @Override
    public List<Result> getByCupIdAndRound(Long cup_id, Long round) {
        return resultRepository.findResultsByCup_idAndRound(cup_id, round);
    }

    @Override
    public void save(Result result) {
        resultRepository.save(result);
    }

    @Override
    public void removeById(Long id) {
        resultRepository.delete(id);
    }
}
