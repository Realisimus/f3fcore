package f3f.data_connector.repository;

import f3f.data_connector.entity.Result;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends CrudRepository<Result, Long> {

    @Query("select r from Result r where r.cup_id = ?1")
    List<Result> findResultsByCup_id(Long id);

    @Query("select r from Result r where r.cup_id = ?1 and r.round = ?2")
    List<Result> findResultsByCup_idAndRound(Long cup_id, Long round);

}
