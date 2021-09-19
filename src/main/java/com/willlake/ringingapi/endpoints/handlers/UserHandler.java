package com.willlake.ringingapi.endpoints.handlers;

import com.willlake.ringingapi.user.data.UserRepoControl;
import com.willlake.ringingapi.utils.RequestStatus;
import com.willlake.ringingapi.utils.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserHandler {
    private final UserRepoControl userRepoControl;

    public UserHandler(UserRepoControl userRepoControl) {
        this.userRepoControl = userRepoControl;
    }

    public ResponseEntity<String> add(String userId, String fName, String sName, String password){
        Status status = userRepoControl.addUser(userId, fName, sName, password);
        if (status.getRequestStatus() == RequestStatus.SUCCSESS){
            return new ResponseEntity<>(status.getMessage(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(status.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
