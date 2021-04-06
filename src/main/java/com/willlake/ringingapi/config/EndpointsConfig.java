package com.willlake.ringingapi.config;

import com.willlake.ringingapi.endpoints.handlers.DatabaseHandler;
import com.willlake.ringingapi.endpoints.MethodHandler;
import com.willlake.ringingapi.methods.data.MethodRepoControl;
import com.willlake.ringingapi.methods.data.MethodRepository;
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
}
