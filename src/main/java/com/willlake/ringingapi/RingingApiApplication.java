package com.willlake.ringingapi;

import com.willlake.ringingapi.methods.data.Method;
import com.willlake.ringingapi.methods.data.MethodRepoControl;
import com.willlake.ringingapi.methods.data.MethodRepository;
import com.willlake.ringingapi.methods.data.ReadMethodsFromFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class RingingApiApplication {
    private final ReadMethodsFromFile readMethodsFromFile;
    private final MethodRepoControl methodRepoControl;

    public RingingApiApplication(ReadMethodsFromFile readMethodsFromFile, MethodRepoControl methodRepoControl) {
        this.readMethodsFromFile = readMethodsFromFile;
        this.methodRepoControl = methodRepoControl;
    }

    private static final Logger log = LoggerFactory.getLogger(RingingApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RingingApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner t(MethodRepository repo, MethodRepoControl methodRepoControl) {
        return (args) -> {
            if (repo.countAll() < 22000) {
                methodRepoControl.addMethodsFromFile();
            }
            log.info(repo.countAll() + " records in the Methods Table");
        };
    }
}
