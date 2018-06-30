package f3f.data_connector.repository;

import f3f.data_connector.entity.Cup;
import f3f.data_connector.entity.Pilot;
import f3f.data_connector.entity.Result;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends CrudRepository<Result, Integer> {

    @Query("select r from Result r where r.cup = ?1")
    List<Result> findByCup(Cup cup);

    @Query("select r from Result r where r.cup = ?1 and r.round = ?2")
    List<Result> findByCupAndRound(Cup cup, Integer round);

    @Query("select r from Result r where r.cup = ?1 and r.pilot = ?2")
    List<Result> findByCupAndPilot(Cup cup, Pilot pilot);

    @Query("select r from Result r where r.cup = ?1 and r.pilot = ?2 and r.round = ?3")
    Result findByCupAndPilotAndRound(Cup cup, Pilot pilot, Integer round);

}
