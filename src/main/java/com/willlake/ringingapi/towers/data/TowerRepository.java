package com.willlake.ringingapi.towers.data;

import com.willlake.ringingapi.databaseObj.Tower;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TowerRepository extends CrudRepository<Tower, Long> {
    Tower findByTowerId(String id);

    @Query("SELECT COUNT(towerId) FROM Tower")
    long countAll();
}
