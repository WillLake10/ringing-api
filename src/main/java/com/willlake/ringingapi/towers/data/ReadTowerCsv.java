package com.willlake.ringingapi.towers.data;

import com.willlake.ringingapi.databaseObj.Tower;
import com.willlake.ringingapi.towers.data.dto.RingType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ReadTowerCsv {
    private static final Logger log = LoggerFactory.getLogger(ReadTowerCsv.class);
    private static final String FILENAME = "src/main/resources/dove.csv";

    public Set<Tower> readCsv() {
        Set<Tower> towers = new HashSet<>();
        boolean first = true;
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!first) {
                    String[] values = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                    RingType ringType = RingType.FULL_CIRCLE;
                    boolean toilet = false;
                    boolean simulator = false;
                    if (Objects.equals(values[1], "Carillon")) {
                        ringType = RingType.CARILLON;
                    }
                    if (Objects.equals(values[22], "T")) {
                        toilet = true;
                    }
                    if (Objects.equals(values[23], "Carillon")) {
                        simulator = true;
                    }

                    try {
                        towers.add(new Tower(
                                values[0],
                                ringType,
                                values[2],
                                values[3],
                                values[5],
                                values[7],
                                values[8],
                                values[10],
                                values[11],
                                values[12],
                                Integer.parseInt(values[13]),
                                Integer.parseInt(values[16]),
                                values[18],
                                values[19],
                                toilet,
                                simulator,
                                values[24],
                                values[28],
                                values[29]));
                    } catch (Exception e){
                        log.warn("Failed to save tower: " + Arrays.toString(values));
                    }

                } else {
                    first = false;
                }
            }
        } catch (IOException e) {
            log.error("File Read Failed", e);
        }
        return towers;
    }
}
