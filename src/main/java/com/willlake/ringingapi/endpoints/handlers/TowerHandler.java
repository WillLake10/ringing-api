package com.willlake.ringingapi.endpoints.handlers;

import com.willlake.ringingapi.databaseObj.Tower;
import com.willlake.ringingapi.towers.data.TowerRepository;

public class TowerHandler {
    private final TowerRepository towerRepository;

    public TowerHandler(TowerRepository towerRepository) {
        this.towerRepository = towerRepository;
    }

    public Iterable<Tower> allTowers() {
        return towerRepository.findAll();
    }

    public Tower getTower(String towerId) {
        return towerRepository.findByTowerId(towerId);
    }
}
