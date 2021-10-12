package com.willlake.ringingapi;

import com.willlake.ringingapi.methods.data.MethodRepoControl;
import com.willlake.ringingapi.methods.data.MethodRepository;
import com.willlake.ringingapi.performances.PerformanceCsvHandling;
import com.willlake.ringingapi.performances.PerformanceIngest;
import com.willlake.ringingapi.performances.PerformanceRepository;
import com.willlake.ringingapi.performances.RingerRepository;
import com.willlake.ringingapi.towers.data.TowerRepoControl;
import com.willlake.ringingapi.towers.data.TowerRepository;
import com.willlake.ringingapi.user.data.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//import com.willlake.ringingapi.methods.data.ReadMethodsFromFile;

@SpringBootApplication
public class RingingApiApplication {
    private static final Logger log = LoggerFactory.getLogger(RingingApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RingingApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner t(MethodRepository methodRepository, MethodRepoControl methodRepoControl, TowerRepository towerRepository, TowerRepoControl towerRepoControl, PerformanceCsvHandling performanceCsvHandling, PerformanceIngest performanceIngest, UserRepository userRepository, PerformanceRepository performanceRepository, RingerRepository ringerRepository) {
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
            log.info(userRepository.countAll() + " records in the User Table");
            log.info(performanceRepository.countAll() + " records in the Performance Table");
            log.info(ringerRepository.countAll() + " records in the Ringer Table");

//            performanceIngest.addPerformanceToDatabase("1452346");
//            performanceIngest.addPerformancesFromSearch("place=york");
//            performanceCsvHandling.exportToCsv();
//            performanceCsvHandling.importFromFile();
        };
    }
}
