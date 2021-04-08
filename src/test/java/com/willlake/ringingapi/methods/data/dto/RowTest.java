package com.willlake.ringingapi.methods.data.dto;

import com.willlake.ringingapi.RingingApiApplication;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class RowTest {
    private static final Logger log = LoggerFactory.getLogger(RingingApiApplication.class);

    @Test
    public void canCreateRoundsRow() {
        Row rounds = new Row(8, true);
        for (int i = 0; i < rounds.getRow().size(); i++){
            assertEquals(i+1, rounds.getRow().get(i).getBellNum());
            assertEquals(i, rounds.getRow().get(i).getBellPos());
        }
    }

    @Test
    public void canCheckIfRounds() {
        Row rounds = new Row(8, true);
        assertTrue(rounds.checkIfRounds());
    }

    @Test
    public void canCheckIfNotRounds() {
        Bell[] bells = new Bell[8];
        for (int i = 0; i < 8; i++){
            bells[7-i] = new Bell(i+1, 7-i);
        }
        Row notRounds = new Row(8, Arrays.asList(bells));
        assertFalse(notRounds.checkIfRounds());
    }

    @Test
    public void canGetShortRowString() {
        Bell[] bells = new Bell[8];
        for (int i = 0; i < 8; i++){
            bells[i] = new Bell(i+1, 7-i);
        }
        Row row = new Row(8, Arrays.asList(bells));
        assertEquals("87654321", row.getShortRow());
    }
}
