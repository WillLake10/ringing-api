package com.willlake.ringingapi.performances;

import com.willlake.ringingapi.databaseObj.Performance;
import com.willlake.ringingapi.databaseObj.Ringer;
import org.springframework.data.repository.CrudRepository;

public interface RingerRepository extends CrudRepository<Ringer, Long> {
}
