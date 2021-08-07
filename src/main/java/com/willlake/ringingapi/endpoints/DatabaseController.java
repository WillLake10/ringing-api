package com.willlake.ringingapi.endpoints;

import com.willlake.ringingapi.endpoints.handlers.DatabaseHandler;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> clear() {
        return databaseHandler.clear();
    }

    @RequestMapping(value = "/methods/addFromFile", method = RequestMethod.GET)
    public ResponseEntity<String> addMethodsFromFile() {
        return databaseHandler.addMethodsFromFile();
    }
}
