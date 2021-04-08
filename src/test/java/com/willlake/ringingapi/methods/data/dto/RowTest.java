package com.willlake.ringingapi.methods.data.dto;

import com.willlake.ringingapi.RingingApiApplication;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RowTest {
    private static final Logger log = LoggerFactory.getLogger(RingingApiApplication.class);

    @Test
    public void canCreateRoundsRow() {
        Row rounds = new Row(8, true);
        log.info(rounds.toString());

//        assertEquals()
        for (int i = 0; i < rounds.getRow().size(); i++){
            assertEquals(i+1, rounds.getRow().get(i).getBellNum());
            assertEquals(i, rounds.getRow().get(i).getBellPos());
        }
    }

}
