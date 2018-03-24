package f3f.data_connector.repository;

import f3f.data_connector.entity.Cup_pilot;
import f3f.data_connector.entity.Pilot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Cup_pilotRepository extends CrudRepository<Cup_pilot, Long> {

    @Query("select c from Cup_pilot c where c.cup_id = ?1")
    List<Cup_pilot> getByCup_id(Long cup_id);

}
