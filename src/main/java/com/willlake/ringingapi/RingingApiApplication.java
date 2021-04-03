package com.willlake.ringingapi;

import com.willlake.ringingapi.methods.data.Method;
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
    private ReadMethodsFromFile readMethodsFromFile;

    @Autowired
    public RingingApiApplication(ReadMethodsFromFile readMethodsFromFile) {
        this.readMethodsFromFile = readMethodsFromFile;
    }

    private static final Logger log = LoggerFactory.getLogger(RingingApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RingingApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner t(MethodRepository repo) {
        return (args) -> {
            Set<Method> returnSet = new HashSet<>(readMethodsFromFile.parseMethodxml());

            returnSet.forEach(m -> repo.save(m));

//            repo.save(new Method("X", "Y", 6, "a", false, 10, "Z", "12"));

            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Method method : repo.findAll()) {
                log.info(method.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Method method = repo.findByMethodId(41571L);
            log.info("Customer found with findById(41571L):");
            log.info("--------------------------------");
            log.info(method.toString());
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            repo.findByName("Freight Treble Place Minimus").forEach(m -> {
                log.info(m.toString());
            });
            // for (Customer bauer : repository.findByLastName("Bauer")) {
            //  log.info(bauer.toString());
            // }
            log.info("");
        };
    }
}
