package com.willlake.ringingapi.performances;

import com.willlake.ringingapi.databaseObj.Performance;
import org.springframework.data.repository.CrudRepository;

public interface RingerRepository extends CrudRepository<Performance, Long> {
}
