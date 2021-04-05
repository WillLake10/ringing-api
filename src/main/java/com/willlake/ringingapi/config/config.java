package com.willlake.ringingapi.config;

import com.willlake.ringingapi.methods.data.MethodRepoControl;
import com.willlake.ringingapi.methods.data.ReadMethodsFromFile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {
    @Bean
    public ReadMethodsFromFile readMethodXML() {
        return new ReadMethodsFromFile();
    }

    @Bean
    public MethodRepoControl methodRepoControl() {
        return new MethodRepoControl();
    }
}
