package com.willlake.ringingapi.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/database")
public class DatabaseController {
    private final DatabaseHandler databaseHandler;

    public DatabaseController(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }

    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public void clear() {
        databaseHandler.clear();
    }
}
