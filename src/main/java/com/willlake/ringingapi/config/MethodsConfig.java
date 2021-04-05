package com.willlake.ringingapi.config;

import com.willlake.ringingapi.methods.data.MethodRepoControl;
import com.willlake.ringingapi.methods.data.MethodRepository;
import com.willlake.ringingapi.methods.data.ReadMethodsFromFile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MethodsConfig {
    @Bean
    public ReadMethodsFromFile readMethodXML() {
        return new ReadMethodsFromFile();
    }

    @Bean
    public MethodRepoControl methodRepoControl(
            MethodRepository methodRepository,
            ReadMethodsFromFile readMethodsFromFile
    ) {
        return new MethodRepoControl(methodRepository, readMethodsFromFile);
    }
}
