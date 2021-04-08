package com.willlake.ringingapi;

import com.willlake.ringingapi.methods.data.MethodRepoControl;
import com.willlake.ringingapi.methods.data.MethodRepository;
//import com.willlake.ringingapi.methods.data.ReadMethodsFromFile;
import com.willlake.ringingapi.methods.data.ReadMethodXml;
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
    public CommandLineRunner t(MethodRepository repo, MethodRepoControl methodRepoControl) {
        return (args) -> {
            log.info("Setting up database...");
            if (repo.countAll() < 22000) {
                methodRepoControl.addMethodsFromFile();
            }
            log.info("Database ready");
            log.info(repo.countAll() + " records in the Methods Table");
        };
    }
}
