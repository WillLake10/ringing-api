package com.willlake.ringingapi.methods.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReadMethodsFromFile {
    private static final Logger log = LoggerFactory.getLogger(ReadMethodsFromFile.class);

    public static List<Method> parseMethodxml() {
        try {
            List<Method> methods = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/CCCBR_methods.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split(",");
//                log.info(String.join(" | ", arr));
                methods.add(new Method(
                        Long.parseLong(arr[0]),
                        arr[1].replace("|", ","),
                        arr[2],
                        Integer.parseInt(arr[5]),
                        symmetry(arr[6]),
                        little(arr[7]),
                        Integer.parseInt(arr[8]),
                        arr[9],
                        placeNot(arr)
                ));
            }
            return methods;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String symmetry(String input) {
        String retString = "";
        if (input.contains("A")) {
            retString = retString + "Asymmetric";
        }
        if (input.contains("D")) {
            if (retString.length() > 0) {
                retString = retString + ", ";
            }
            retString = retString + "Double symmetry";
        }
        if (input.contains("P")) {
            if (retString.length() > 0) {
                retString = retString + ", ";
            }
            retString = retString + "Palindromic symmetry";
        }
        if (input.contains("R")) {
            if (retString.length() > 0) {
                retString = retString + ", ";
            }
            retString = retString + "Rotational symmetry";
        }
        return retString;
    }

    private static Boolean little(String input) {
        return input.contains("Y");
    }

    private static String placeNot(String[] arr) {
        StringBuilder t = new StringBuilder();
        for (int i = 10; i < arr.length; i++){
            if (i != 10){
                t.append(".");
            }
            t.append(arr[i]);
        }
        return t.toString();
    }
}
