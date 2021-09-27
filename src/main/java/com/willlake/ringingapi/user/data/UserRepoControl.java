package com.willlake.ringingapi.user.data;

import com.willlake.ringingapi.databaseObj.User;
import com.willlake.ringingapi.utils.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class UserRepoControl {
    private static final Logger log = LoggerFactory.getLogger(UserRepoControl.class);

    private final UserRepository repository;
    private final PasswordAuth auth;

    public UserRepoControl(UserRepository repository, PasswordAuth auth) {
        this.repository = repository;
        this.auth = auth;
    }

    public boolean userIdInUse(String userId){
        return repository.countOfUserWithId(userId) != 0;
    }

    public Status addUser(String userId, String fName, String sName, String pWord) {
        if (userIdInUse(userId)){
            return new Status(HttpStatus.CONFLICT, "Username Already in use");
        } else {
            User user = new User(userId, fName, sName, auth.hash(pWord));
            repository.save(user);
            log.info("New User " + userId + " added");
            return new Status(HttpStatus.OK, "User " + userId + " successfully added");
        }
    }

    public void clearAllUsersFromDB() {
        repository.deleteAll();
    }
}
