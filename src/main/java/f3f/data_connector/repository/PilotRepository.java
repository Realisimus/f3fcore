package f3f.data_connector.repository;

import f3f.data_connector.entity.Pilot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PilotRepository extends CrudRepository<Pilot, Long> {

}
