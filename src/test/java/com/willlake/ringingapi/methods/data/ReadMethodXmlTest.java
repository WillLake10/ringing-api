package com.willlake.ringingapi.methods.data;

import com.willlake.ringingapi.RingingApiApplication;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadMethodXmlTest {
    ReadMethodXml readMethodXml = new ReadMethodXml();

    private static final Logger log = LoggerFactory.getLogger(RingingApiApplication.class);

    @Test
    public void data() {
        readMethodXml.readXml();
    }
}
