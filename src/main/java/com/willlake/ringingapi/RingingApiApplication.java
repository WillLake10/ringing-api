package com.willlake.ringingapi;

import com.willlake.ringingapi.methods.data.MethodRepoControl;
import com.willlake.ringingapi.methods.data.MethodRepository;
//import com.willlake.ringingapi.methods.data.ReadMethodsFromFile;
import com.willlake.ringingapi.performances.PerformanceIngest;
import com.willlake.ringingapi.performances.PerformancesRequester;
import com.willlake.ringingapi.towers.data.TowerRepoControl;
import com.willlake.ringingapi.towers.data.TowerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RingingApiApplication {
    private static final Logger log = LoggerFactory.getLogger(RingingApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RingingApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner t(MethodRepository methodRepository, MethodRepoControl methodRepoControl, TowerRepository towerRepository, TowerRepoControl towerRepoControl, PerformanceIngest performanceIngest) {
        return (args) -> {
            log.info("Setting up database...");
            if (methodRepository.countAll() < 22000) {
                methodRepoControl.addMethodsFromFile();
            }
            if (towerRepository.countAll() < 7000) {
                towerRepoControl.addTowersFromFile();
            }
            log.info("Database ready");
            log.info(methodRepository.countAll() + " records in the Methods Table");
            log.info(towerRepository.countAll() + " records in the Towers Table");

            performanceIngest.addPerformanceToDatabase("1452346");
        };
    }
}
