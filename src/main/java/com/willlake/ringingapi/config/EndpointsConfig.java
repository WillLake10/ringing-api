package com.willlake.ringingapi.config;

import com.willlake.ringingapi.endpoints.handlers.*;
import com.willlake.ringingapi.methods.data.MethodRepoControl;
import com.willlake.ringingapi.methods.data.MethodRepository;
import com.willlake.ringingapi.performances.PerformanceIngest;
import com.willlake.ringingapi.towers.data.TowerRepoControl;
import com.willlake.ringingapi.towers.data.TowerRepository;
import com.willlake.ringingapi.user.data.UserRepoControl;
import com.willlake.ringingapi.user.data.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndpointsConfig {
    @Bean
    public DatabaseHandler databaseHandler(
            MethodRepoControl methodRepoControl,
            MethodRepository methodRepository,
            TowerRepoControl towerRepoControl,
            TowerRepository towerRepository,
            UserRepoControl userRepoControl,
            UserRepository userRepository
    ) {
        return new DatabaseHandler(methodRepoControl, methodRepository, towerRepoControl, towerRepository, userRepoControl, userRepository);
    }

    @Bean
    public MethodHandler methodHandler(MethodRepository methodRepository) {
        return new MethodHandler(methodRepository);
    }

    @Bean
    public TowerHandler towerHandler(TowerRepository towerRepository) {
        return new TowerHandler(towerRepository);
    }

    @Bean
    public UserHandler userHandler(UserRepoControl userRepoControl) {
        return new UserHandler(userRepoControl);
    }

    @Bean
    public PerformanceHandler performanceHandler(PerformanceIngest performanceIngest) {
        return new PerformanceHandler(performanceIngest);
    }
}
