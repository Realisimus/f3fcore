package f3f.data_connector.repository;

import f3f.data_connector.entity.CupDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CupDetailRepository extends CrudRepository<CupDetail, Integer>{
}
