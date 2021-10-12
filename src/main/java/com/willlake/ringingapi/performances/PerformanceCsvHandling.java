package com.willlake.ringingapi.performances;

import com.willlake.ringingapi.databaseObj.Performance;
import com.willlake.ringingapi.databaseObj.Ringer;
import com.willlake.ringingapi.utils.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class PerformanceCsvHandling {
    private static final Logger log = LoggerFactory.getLogger(PerformanceCsvHandling.class);
    private static final String PERFORMANCES_CSV = "src/main/resources/performances.csv";
    private static final String RINGERS_CSV = "src/main/resources/ringers.csv";

    private final PerformanceRepository performanceRepository;
    private final RingerRepository ringerRepository;

    public PerformanceCsvHandling(PerformanceRepository performanceRepository, RingerRepository ringerRepository) {
        this.performanceRepository = performanceRepository;
        this.ringerRepository = ringerRepository;
    }

    public Status exportToCsv() {
        Iterable<Performance> allPerformance = performanceRepository.findAll();
        try {
            clearFile(PERFORMANCES_CSV);

            FileOutputStream f = new FileOutputStream(PERFORMANCES_CSV);
            ObjectOutputStream o = new ObjectOutputStream(f);

            allPerformance.forEach(it -> {
                try {
                    o.writeObject(it);
                } catch (IOException e) {
                    log.error("Error initializing stream", e);
                }
            });

            o.close();
            f.close();
        } catch (IOException e) {
            log.error("Error initializing stream", e);
        }
        Iterable<Ringer> allRingers = ringerRepository.findAll();
        try {
            clearFile(RINGERS_CSV);

            FileOutputStream f = new FileOutputStream(RINGERS_CSV);
            ObjectOutputStream o = new ObjectOutputStream(f);

            allRingers.forEach(it -> {
                try {
                    o.writeObject(it);
                } catch (IOException e) {
                    log.error("Error initializing stream", e);
                }
            });

            o.close();
            f.close();
        } catch (IOException e) {
            log.error("Error initializing stream", e);
        }
        return new Status(HttpStatus.ACCEPTED, "");
    }

    public Status importFromFile() {
        try {
            FileInputStream fi = new FileInputStream(PERFORMANCES_CSV);
            ObjectInputStream oi = new ObjectInputStream(fi);

            Set<Performance> performances = new HashSet<>();
            int i = 0;
            while (true) {
                try {
                    Performance performance = (Performance) oi.readObject();
//                    log.info(performance.toString());
                    performances.add(performance);
                } catch (EOFException e) {
                    log.info("End of File");
                    break;
                }
                i++;
                if (i%100 == 0){
                    log.info(i + " performances read");
                }
            }

            performanceRepository.saveAll(performances);
            log.info("Performances saved to db");

            fi.close();
            oi.close();
        } catch (IOException | ClassNotFoundException e) {
            log.error("Error initializing stream", e);
        }
        try {
            FileInputStream fi = new FileInputStream(RINGERS_CSV);
            ObjectInputStream oi = new ObjectInputStream(fi);

            Set<Ringer> ringers = new HashSet<>();
            int i = 0;
            while (true) {
                try {
                    Ringer ringer = (Ringer) oi.readObject();
//                    log.info(ringer.toString());
                    ringers.add(ringer);
                } catch (EOFException e) {
                    log.info("End of File");
                    break;
                }
                i++;
                if (i%100 == 0){
                    log.info(i + " ringers read");
                }
            }

            ringerRepository.saveAll(ringers);
            log.info("Ringers saved to db");

            fi.close();
            oi.close();
        } catch (IOException | ClassNotFoundException e) {
            log.error("Error initializing stream", e);
        }
        return new Status(HttpStatus.ACCEPTED, "");
    }

    private void clearFile(String filename) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(filename);
        writer.print("");
        writer.close();
    }
}
