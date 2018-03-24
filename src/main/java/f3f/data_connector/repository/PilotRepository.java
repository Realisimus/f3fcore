package f3f.data_connector.repository;

import f3f.data_connector.entity.Pilot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PilotRepository extends CrudRepository<Pilot, Long> {

}
