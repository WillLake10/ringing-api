package com.willlake.ringingapi.endpoints.handlers;

import com.willlake.ringingapi.methods.data.MethodRepoControl;
import com.willlake.ringingapi.methods.data.MethodRepository;
import com.willlake.ringingapi.performances.PerformanceCsvHandling;
import com.willlake.ringingapi.performances.PerformanceRepository;
import com.willlake.ringingapi.performances.RingerRepository;
import com.willlake.ringingapi.towers.data.TowerRepoControl;
import com.willlake.ringingapi.towers.data.TowerRepository;
import com.willlake.ringingapi.user.data.UserRepoControl;
import com.willlake.ringingapi.user.data.UserRepository;
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
    private UserRepoControl userRepoControl;
    private UserRepository userRepository;
    private PerformanceRepository performanceRepository;
    private PerformanceCsvHandling performanceCsvHandling;
    private RingerRepository ringerRepository;

    public DatabaseHandler(MethodRepoControl methodRepoControl, MethodRepository methodRepository, TowerRepoControl towerRepoControl, TowerRepository towerRepository, UserRepoControl userRepoControl, UserRepository userRepository, PerformanceRepository performanceRepository, PerformanceCsvHandling performanceCsvHandling, RingerRepository ringerRepository) {
        this.methodRepoControl = methodRepoControl;
        this.methodRepository = methodRepository;
        this.towerRepoControl = towerRepoControl;
        this.towerRepository = towerRepository;
        this.userRepoControl = userRepoControl;
        this.userRepository = userRepository;
        this.performanceRepository = performanceRepository;
        this.performanceCsvHandling = performanceCsvHandling;
        this.ringerRepository = ringerRepository;
    }

    public ResponseEntity<String> clear() {
        methodRepoControl.clearAllMethodsFromDB();
        log.info("Method table cleared");
        towerRepoControl.clearAllTowersFromDB();
        log.info("Tower table cleared");
        userRepoControl.clearAllUsersFromDB();
        log.info("User table cleared");
        performanceRepository.deleteAll();
        log.info("Performance table cleared");
        ringerRepository.deleteAll();
        log.info("Ringer table cleared");


        log.info(methodRepository.countAll() + " records in the Methods Table");
        log.info(towerRepository.countAll() + " records in the Tower Table");
        log.info(userRepository.countAll() + " records in the Tower Table");
        log.info(performanceRepository.countAll() + " records in the Tower Table");
        log.info(ringerRepository.countAll() + " records in the Tower Table");

        if (methodRepository.countAll() == 0 && towerRepository.countAll() == 0 && userRepository.countAll() == 0){
            return new ResponseEntity<>("All tables successfully cleared", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Something when wrong: not all tables cleared", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> addMethodsFromFile() {
        addMethods();
        if (methodRepository.countAll() >= 22000){
            return new ResponseEntity<>("All methods successfully added", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Something when wrong: not all methods added", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> addTowersFromFile() {
        addTowers();
        if (towerRepository.countAll() >= 7000){
            return new ResponseEntity<>("All towers successfully added", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Something when wrong: not all towers added", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> addPerformancesFromFile() {
        addPerformancesFromCsv();
        return new ResponseEntity<>("All performances and ringers successfully added", HttpStatus.OK);
    }

    public ResponseEntity<String> addAllFromFile() {
        addMethods();
        addTowers();
        log.info(towerRepository.countAll() + " records in the Towers Table");
        if (methodRepository.countAll() >= 22000 && towerRepository.countAll() >= 7000){
            return new ResponseEntity<>("All methods and towers successfully added", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Something when wrong: not all methods and towers added", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void addMethods(){
        methodRepoControl.addMethodsFromFile();
        log.info(methodRepository.countAll() + " records in the Methods Table");
    }

    private void addTowers(){
        towerRepoControl.addTowersFromFile();
        log.info(towerRepository.countAll() + " records in the Towers Table");
    }

    private void addPerformancesFromCsv(){
        performanceCsvHandling.importFromFile();
        log.info(performanceRepository.countAll() + " records in the Performances Table");
        log.info(ringerRepository.countAll() + " records in the Ringers Table");
    }
}
