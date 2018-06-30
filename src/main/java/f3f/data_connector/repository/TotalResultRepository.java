package f3f.data_connector.repository;

import f3f.data_connector.entity.Cup;
import f3f.data_connector.entity.Pilot;
import f3f.data_connector.entity.TotalResult;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TotalResultRepository extends CrudRepository<TotalResult, Integer> {

    @Query("select c from TotalResult c where c.cup = ?1")
    List<TotalResult> findByCup(Cup cup);

    @Query("select c.pilot from TotalResult c where c.cup = ?1")
    List<Pilot> findPilotsByCup(Cup cup);

    @Query("select count(c) from TotalResult c where c.cup = ?1")
    Integer findNumberOfPilotsByCup(Cup cup);

}
