package f3f.data_connector.repository;

import f3f.data_connector.entity.Cup_pilot;
import f3f.data_connector.entity.Pilot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Cup_pilotRepository extends CrudRepository<Cup_pilot, Long> {

    @Query("SELECT r FROM RESULTS r WHERE r.CUP_ID = ?1")
    List<Pilot> findPilotsByCup_id(Long id);

}
