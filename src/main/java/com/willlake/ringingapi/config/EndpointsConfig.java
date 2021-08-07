package com.willlake.ringingapi.config;

import com.willlake.ringingapi.endpoints.handlers.DatabaseHandler;
import com.willlake.ringingapi.endpoints.handlers.MethodHandler;
import com.willlake.ringingapi.endpoints.handlers.TowerHandler;
import com.willlake.ringingapi.methods.data.MethodRepoControl;
import com.willlake.ringingapi.methods.data.MethodRepository;
import com.willlake.ringingapi.towers.data.TowerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndpointsConfig {
    @Bean
    public DatabaseHandler databaseHandler(
            MethodRepoControl methodRepoControl,
            MethodRepository methodRepository
    ) {
        return new DatabaseHandler(methodRepoControl, methodRepository);
    }

    @Bean
    public MethodHandler methodHandler(MethodRepository methodRepository) {
        return new MethodHandler(methodRepository);
    }

    @Bean
    public TowerHandler towerHandler(TowerRepository towerRepository) {
        return new TowerHandler(towerRepository);
    }
}
