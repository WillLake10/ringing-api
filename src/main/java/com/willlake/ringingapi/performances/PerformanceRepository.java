package com.willlake.ringingapi.performances;

import com.willlake.ringingapi.databaseObj.Performance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PerformanceRepository extends CrudRepository<Performance, Long> {
    Performance findByPerformanceId(String id);

    Iterable<Performance> findAll();

    @Query("SELECT COUNT(performanceId) FROM Performance ")
    long countAll();
}
