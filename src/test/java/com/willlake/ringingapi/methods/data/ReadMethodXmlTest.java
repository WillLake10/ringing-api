package com.willlake.ringingapi.methods.data;

import com.willlake.ringingapi.RingingApiApplication;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadMethodXmlTest {
    ReadMethodXml readMethodXml = new ReadMethodXml();

    private static final Logger log = LoggerFactory.getLogger(ReadMethodXmlTest.class);

    @Test
    public void canGetLongNotationLeftSymmetry() {
        assertEquals("x.18.x.18.x.18.x.18.x.18.x.18.x.18.x.12", readMethodXml.getLongNotation("-18-18-18-18,12"));
    }

    @Test
    public void canGetLongNotationRightSymmetry() {
        assertEquals("3.1.5.1.5.1.5.1.5.1", readMethodXml.getLongNotation("3,1.5.1.5.1"));
    }

    @Test
    public void canGetLongNotationNoSymmetry() {
        assertEquals(
                "x.38.x.14.x.58.x.1236.x.14.x.58.x.16.34.78.34.16.x.58.x.14.x.36.x.1258.x.14.x.38.x.12",
                readMethodXml.getLongNotation("-38-14-58-1236-14-58-16.34.78.34.16-58-14-36-1258-14-38-12")
        );
    }
}
