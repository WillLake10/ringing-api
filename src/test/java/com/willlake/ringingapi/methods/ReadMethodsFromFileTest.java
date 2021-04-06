package com.willlake.ringingapi.methods;

import com.willlake.ringingapi.RingingApiApplication;
import com.willlake.ringingapi.methods.data.dto.Method;
import com.willlake.ringingapi.methods.data.ReadMethodsFromFile;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ReadMethodsFromFileTest {
    ReadMethodsFromFile readMethodsFromFile = new ReadMethodsFromFile();

    private static final Logger log = LoggerFactory.getLogger(RingingApiApplication.class);

    @Test
    public void canParseCsvMethod() {
        String input = "12834,Plain Bob Major,1725-04-26,RW:1959/651,,8,P,,16,a,-,18,-,18,-,18,-,18,-,18,-,18,-,18,-,12,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,";

        Method method = readMethodsFromFile.parseMethod(input);

        assertEquals(12834, method.getMethodId());
        assertEquals("Plain Bob Major", method.getName());
        assertEquals("1725-04-26", method.getFirstRung());
        assertEquals(8, method.getStage());
        assertEquals("Palindromic Symmetry", method.getSymmetry());
        assertEquals(false, method.getLittle());
        assertEquals(16, method.getLengthOfLead());
        assertEquals("a", method.getLeadHead());
        assertEquals("x.18.x.18.x.18.x.18.x.18.x.18.x.18.x.12", method.getNotation());
    }

    @Test
    public void canGetMethodsSymmetry() {
        assertEquals("Asymmetric Symmetry", readMethodsFromFile.decodeSymmetry("A"));
        assertEquals("Double Symmetry", readMethodsFromFile.decodeSymmetry("D"));
        assertEquals("Palindromic Symmetry", readMethodsFromFile.decodeSymmetry("P"));
        assertEquals("Rotational Symmetry", readMethodsFromFile.decodeSymmetry("R"));
        assertEquals("Double, Palindromic and Rotational Symmetry", readMethodsFromFile.decodeSymmetry("DPR"));
    }

    @Test
    public void canFindLeadHead() {
        String input = "12834,Plain Bob Major,1725-04-26,RW:1959/651,,8,P,,16,a,-,18,-,18,-,18,-,18,-,18,-,18,-,18,-,12,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,";

        readMethodsFromFile.findLeadHead(input.split(","));
    }
}
