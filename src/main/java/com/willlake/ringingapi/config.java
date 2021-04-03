package com.willlake.ringingapi;

import com.willlake.ringingapi.methods.data.ReadMethodsFromFile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {
    @Bean
    public ReadMethodsFromFile readMethodXML() {
        return new ReadMethodsFromFile();
    }
}
