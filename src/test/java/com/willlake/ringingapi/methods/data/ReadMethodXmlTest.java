package com.willlake.ringingapi.methods.data;

import com.willlake.ringingapi.RingingApiApplication;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadMethodXmlTest {
    ReadMethodXml readMethodXml = new ReadMethodXml();

    private static final Logger log = LoggerFactory.getLogger(RingingApiApplication.class);

    @Test
    public void data() {
        readMethodXml.readXml();
    }

    @Test
    public void canGetLongNotation() {
        String inputString = "-18-18-18-18,12";

        assertEquals("x.18.x.18.x.18.x.18.x.18.x.18.x.18.x.12", readMethodXml.getLongNotation(inputString));
    }
}
