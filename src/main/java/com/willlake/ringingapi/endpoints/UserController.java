package com.willlake.ringingapi.endpoints;

import com.willlake.ringingapi.endpoints.dto.UserJson;
import com.willlake.ringingapi.endpoints.handlers.UserHandler;
import com.willlake.ringingapi.user.data.PasswordAuth;
import com.willlake.ringingapi.utils.RequestStatus;
import com.willlake.ringingapi.utils.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserHandler userHandler;
    PasswordAuth passwordAuth = new PasswordAuth();

    public UserController(UserHandler userHandler) {
        this.userHandler = userHandler;
    }

    @RequestMapping(value = "/password/encrypt/{pword}", method = RequestMethod.GET)
    public String password(@PathVariable String pword) {
        return passwordAuth.hash(pword);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> add(@RequestBody UserJson userJson) {
        return userHandler.add(userJson.getUserId(), userJson.getfName(), userJson.getsName(), userJson.getPassword());
    }
}
