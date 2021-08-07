package com.willlake.ringingapi.config;

import com.willlake.ringingapi.towers.data.ReadTowerCsv;
import com.willlake.ringingapi.towers.data.TowerRepoControl;
import com.willlake.ringingapi.towers.data.TowerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TowerConfig {
    @Bean
    public ReadTowerCsv readTowerCsv() {
        return  new ReadTowerCsv();
    }

    @Bean
    public TowerRepoControl towerRepoControl(
            TowerRepository towerRepository,
            ReadTowerCsv readTowerCsv
    ) {
        return new TowerRepoControl(towerRepository, readTowerCsv);
    }
}
