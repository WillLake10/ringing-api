package com.willlake.ringingapi.endpoints;

import com.willlake.ringingapi.endpoints.handlers.DatabaseHandler;
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

    @RequestMapping(value = "/addFromFile", method = RequestMethod.GET)
    public ResponseEntity<String> addAllFromFile() {
        return databaseHandler.addAllFromFile();
    }

    @RequestMapping(value = "/addFromFile/methods", method = RequestMethod.GET)
    public ResponseEntity<String> addMethodsFromFile() {
        return databaseHandler.addMethodsFromFile();
    }

    @RequestMapping(value = "/addFromFile/towers", method = RequestMethod.GET)
    public ResponseEntity<String> addTowersFromFile() {
        return databaseHandler.addTowersFromFile();
    }

    @RequestMapping(value = "/addFromFile/performances", method = RequestMethod.GET)
    public ResponseEntity<String> addPerformancesFromFile() {
        return databaseHandler.addPerformancesFromFile();
    }


}
