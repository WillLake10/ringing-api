package com.willlake.ringingapi.methods.data;

import com.willlake.ringingapi.databaseObj.Method;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MethodRepository extends CrudRepository<Method, Long> {
    List<Method> findByName(String name);

    Method findByMethodId(String id);

    @Query("SELECT COUNT(methodId) FROM Method")
    long countAll();
}
