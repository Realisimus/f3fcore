package f3f.data_connector.repository;

import f3f.data_connector.entity.Cup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CupRepository extends CrudRepository<Cup, Integer>{
}
