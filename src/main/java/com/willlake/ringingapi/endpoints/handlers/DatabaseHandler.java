package com.willlake.ringingapi.endpoints.handlers;

import com.willlake.ringingapi.methods.data.MethodRepoControl;
import com.willlake.ringingapi.methods.data.MethodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DatabaseHandler {
    private static final Logger log = LoggerFactory.getLogger(DatabaseHandler.class);

    private MethodRepoControl methodRepoControl;
    private MethodRepository methodRepository;

    public DatabaseHandler(MethodRepoControl methodRepoControl, MethodRepository methodRepository) {
        this.methodRepoControl = methodRepoControl;
        this.methodRepository = methodRepository;
    }

    public ResponseEntity<String> clear() {
        methodRepoControl.clearAllMethodsFromDB();
        log.info("Method table cleared");
        log.info(methodRepository.countAll() + " records in the Methods Table");
        if (methodRepository.countAll() == 0){
            return new ResponseEntity<>("All methods successfully cleared", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Something when wrong: not all methods cleared", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> addMethodsFromFile() {
        methodRepoControl.addMethodsFromFile();
        log.info(methodRepository.countAll() + " records in the Methods Table");
        if (methodRepository.countAll() >= 22000){
            return new ResponseEntity<>("All methods successfully added", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Something when wrong: not all methods added", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
