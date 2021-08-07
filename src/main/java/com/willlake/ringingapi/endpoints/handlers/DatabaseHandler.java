package com.willlake.ringingapi.endpoints.handlers;

import com.willlake.ringingapi.methods.data.MethodRepoControl;
import com.willlake.ringingapi.methods.data.MethodRepository;
import com.willlake.ringingapi.towers.data.TowerRepoControl;
import com.willlake.ringingapi.towers.data.TowerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DatabaseHandler {
    private static final Logger log = LoggerFactory.getLogger(DatabaseHandler.class);

    private MethodRepoControl methodRepoControl;
    private MethodRepository methodRepository;
    private TowerRepoControl towerRepoControl;
    private TowerRepository towerRepository;

    public DatabaseHandler(MethodRepoControl methodRepoControl, MethodRepository methodRepository, TowerRepoControl towerRepoControl, TowerRepository towerRepository) {
        this.methodRepoControl = methodRepoControl;
        this.methodRepository = methodRepository;
        this.towerRepoControl = towerRepoControl;
        this.towerRepository = towerRepository;
    }

    public ResponseEntity<String> clear() {
        methodRepoControl.clearAllMethodsFromDB();
        log.info("Method table cleared");
        towerRepoControl.clearAllTowersFromDB();
        log.info("Tower table cleared");
        log.info(methodRepository.countAll() + " records in the Methods Table");
        log.info(towerRepository.countAll() + " records in the Tower Table");
        if (methodRepository.countAll() == 0 && towerRepository.countAll() == 0){
            return new ResponseEntity<>("All methods and towers successfully cleared", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Something when wrong: not all methods and towers cleared", HttpStatus.INTERNAL_SERVER_ERROR);
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

    public ResponseEntity<String> addTowersFromFile() {
        towerRepoControl.addTowersFromFile();
        log.info(towerRepository.countAll() + " records in the Towers Table");
        if (towerRepository.countAll() >= 7000){
            return new ResponseEntity<>("All towers successfully added", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Something when wrong: not all towers added", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> addAllFromFile() {
        methodRepoControl.addMethodsFromFile();
        log.info(methodRepository.countAll() + " records in the Methods Table");
        towerRepoControl.addTowersFromFile();
        log.info(towerRepository.countAll() + " records in the Towers Table");
        if (methodRepository.countAll() >= 22000 && towerRepository.countAll() >= 7000){
            return new ResponseEntity<>("All methods and towers successfully added", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Something when wrong: not all methods and towers added", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
