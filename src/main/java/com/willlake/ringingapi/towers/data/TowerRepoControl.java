package com.willlake.ringingapi.towers.data;

import com.willlake.ringingapi.databaseObj.Tower;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class TowerRepoControl {
    private static final Logger log = LoggerFactory.getLogger(TowerRepoControl.class);

    private final TowerRepository repository;
    private final ReadTowerCsv readTowerCsv;

    public TowerRepoControl(TowerRepository repository, ReadTowerCsv readTowerCsv) {
        this.repository = repository;
        this.readTowerCsv = readTowerCsv;
    }

    public void addTowersFromFile() {
        log.info("Adding Towers to database");
        Set<Tower> returnSet = new HashSet<>(readTowerCsv.readCsv());
        repository.saveAll(returnSet);
        log.info("Added " + (long) returnSet.size() + " Towers to database");
    }

    public void clearAllTowersFromDB() {
        repository.deleteAll();
    }
}
