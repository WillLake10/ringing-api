package com.willlake.ringingapi.methods.data;

import com.willlake.ringingapi.methods.data.dto.Method;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MethodRepository extends CrudRepository<Method, Long> {
    List<Method> findByName(String name);

    Method findByMethodId(Long id);

    @Query("SELECT COUNT(methodId) FROM Method")
    long countAll();
}
