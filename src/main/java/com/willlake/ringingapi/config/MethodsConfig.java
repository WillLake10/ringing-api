package com.willlake.ringingapi.config;

import com.willlake.ringingapi.methods.data.MethodRepoControl;
import com.willlake.ringingapi.methods.data.MethodRepository;
import com.willlake.ringingapi.methods.data.ReadMethodXml;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MethodsConfig {
    @Bean
    public ReadMethodXml readMethodXML() {
        return new ReadMethodXml();
    }

    @Bean
    public MethodRepoControl methodRepoControl(
            MethodRepository methodRepository,
            ReadMethodXml readMethodXml
    ) {
        return new MethodRepoControl(methodRepository, readMethodXml);
    }
}
