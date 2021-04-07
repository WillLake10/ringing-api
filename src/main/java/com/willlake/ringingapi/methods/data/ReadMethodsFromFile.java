package com.willlake.ringingapi.methods.data;

import com.willlake.ringingapi.RingingApiApplication;
import com.willlake.ringingapi.methods.data.dto.Lead;
import com.willlake.ringingapi.methods.data.dto.Method;
import com.willlake.ringingapi.methods.data.dto.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class ReadMethodsFromFile {

    private static final Logger log = LoggerFactory.getLogger(RingingApiApplication.class);

    public List<Method> parseMethodxml() {
        try {
            List<Method> methods = new ArrayList<>();
            BufferedReader reader = getFile();
            String line;
            while ((line = reader.readLine()) != null) {
//                log.info(line);
                methods.add(parseMethod(line));
//                log.info("done");
            }
            return methods;
        } catch (Exception e) {
            throw new NullPointerException();
        }
    }

    public Method parseMethod(String line) {
        String[] arr = line.split(",");
        return new Method(
                Long.parseLong(arr[0]),
                arr[1].replace("|", ","),
                arr[2],
                Integer.parseInt(arr[5]),
                decodeSymmetry(arr[6]),
                little(arr[7]),
                Integer.parseInt(arr[8]),
                findLeadHead(arr),
                placeNot(arr)
        );
    }

    private BufferedReader getFile() throws FileNotFoundException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/CCCBR_methods.csv"));
            return reader;
        } catch (Exception e) {
            throw new FileNotFoundException();
        }
    }

    public String decodeSymmetry(String input) {
        List<String> syms = new ArrayList<>();
        if (input.contains("A")) {
            syms.add("Asymmetric");
        }
        if (input.contains("D")) {
            syms.add("Double");
        }
        if (input.contains("P")) {
            syms.add("Palindromic");
        }
        if (input.contains("R")) {
            syms.add("Rotational");
        }
        StringBuilder retString = new StringBuilder();
        if (syms.size() > 1) {
            for (int i = 0; i < syms.size(); i++) {
                if (i == 0) {
                    retString.append(syms.get(i));
                } else if (i == syms.size() - 1) {
                    retString.append(" and ").append(syms.get(i)).append(" Symmetry");
                } else {
                    retString.append(", ").append(syms.get(i));
                }
            }
        } else {
            retString.append(syms.get(0)).append(" Symmetry");
        }

        return retString.toString();
    }

    private Boolean little(String input) {
        return input.contains("Y");
    }

    private String placeNot(String[] arr) {
        StringBuilder t = new StringBuilder();
        for (int i = 10; i < arr.length; i++) {
            if (i != 10) {
                t.append(".");
            }
            if (arr[i].equals("-")) {
                t.append("x");
            } else {
                t.append(arr[i]);
            }
        }
        return t.toString();
    }

    public String findLeadHead(String[] arr) {
//        log.info("Leadhead started");
        String fullNot = placeNot(arr);
        String[] placeNotation = fullNot.split("\\.");
//        log.info(Arrays.toString(placeNotation));
        Lead firstLead = new Lead();
        firstLead.addRow(new Row(Integer.parseInt(arr[5]), true));
        for (int i = 0; i < placeNotation.length; i++) {
            firstLead.addNextRow(placeNotation[i]);
//            log.info("Leadhead row " + i + " added");
        }
//        log.info("Leadhead Finished");
        return firstLead.getRows().get(firstLead.getRows().size() - 1).toShortString();
    }
}
