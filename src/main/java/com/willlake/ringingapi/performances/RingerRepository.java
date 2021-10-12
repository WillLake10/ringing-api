package com.willlake.ringingapi.performances;

import com.willlake.ringingapi.databaseObj.Ringer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RingerRepository extends CrudRepository<Ringer, Long> {
    @Query("SELECT COUNT(name) FROM Ringer")
    long countAll();
}
