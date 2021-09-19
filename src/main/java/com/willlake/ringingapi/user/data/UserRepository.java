package com.willlake.ringingapi.user.data;

import com.willlake.ringingapi.databaseObj.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserId(String id);

    @Query("SELECT COUNT(userId) FROM User ")
    long countAll();

    @Query("SELECT COUNT(userId) FROM User WHERE userId = :id")
    long countOfUserWithId(@Param("id") String id);
}
