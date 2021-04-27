package com.willlake.ringingapi.methods.data;

import com.willlake.ringingapi.databaseObj.Method;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReadMethodXml {
    private static final String FILENAME = "src/main/resources/CCCBR_methods.xml";

    public Set<Method> readXml() {
        Set<Method> methods = new HashSet<>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(FILENAME));
            doc.getDocumentElement().normalize();

            NodeList methodSetNodeList = doc.getElementsByTagName("methodSet");

            for (int listIt = 0; listIt < methodSetNodeList.getLength(); listIt++) {

                Node methodSetNode = methodSetNodeList.item(listIt);

                if (methodSetNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element listElement = (Element) methodSetNode;
                    Element propNode = (Element) listElement.getElementsByTagName("properties").item(0);

                    String stage = "";
                    String classification = "";
                    String lengthOfLead = "";
                    String numberOfHunts = "";
                    String huntbellPath = "";
                    String leadHead = "";
                    String leadHeadCode = "";
                    String symmetry = "";
                    boolean little = false;
                    boolean differential = false;
                    boolean plain = false;
                    boolean trebleDodging = false;

                    String methodId;
                    String notation = "";
                    String title = "";
                    String name = "";

                    NodeList clasificationNodeList = listElement.getElementsByTagName("classification");

                    stage = getElementTag(stage, "stage", propNode);
                    classification = getElementTag(classification, "classification", propNode);
                    lengthOfLead = getElementTag(lengthOfLead, "lengthOfLead", propNode);
                    numberOfHunts = getElementTag(numberOfHunts, "numberOfHunts", propNode);
                    huntbellPath = getElementTag(huntbellPath, "huntbellPath", propNode);
                    leadHead = getElementTag(leadHead, "leadHead", propNode);
                    leadHeadCode = getElementTag(leadHeadCode, "leadHeadCode", propNode);
                    symmetry = getElementTag(symmetry, "symmetry", propNode);

                    little = getAtribute(little, "little", clasificationNodeList);
                    differential = getAtribute(differential, "differential", clasificationNodeList);
                    plain = getAtribute(plain, "plain", clasificationNodeList);
                    trebleDodging = getAtribute(trebleDodging, "trebleDodging", clasificationNodeList);


                    NodeList methodNodeList = listElement.getElementsByTagName("method");


                    for (int methodIt = 0; methodIt < methodNodeList.getLength(); methodIt++) {

                        methodId = methodNodeList.item(methodIt).getAttributes().getNamedItem("id").getTextContent();
                        Node methodNode = methodNodeList.item(methodIt);

                        if (methodSetNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element methodElement = (Element) methodNode;

                            NodeList methodClasificationNodeList = methodElement.getElementsByTagName("classification");

                            name = getElementTag(name, "name", methodElement);
                            classification = getElementTag(classification, "classification", methodElement);
                            title = getElementTag(title, "title", methodElement);
                            stage = getElementTag(stage, "stage", methodElement);
                            notation = getLongNotation(getElementTag(notation, "notation", methodElement));
                            lengthOfLead = getElementTag(lengthOfLead, "lengthOfLead", methodElement);
                            numberOfHunts = getElementTag(numberOfHunts, "numberOfHunts", methodElement);
                            huntbellPath = getElementTag(huntbellPath, "huntbellPath", methodElement);
                            leadHead = getElementTag(leadHead, "leadHead", methodElement);
                            leadHeadCode = getElementTag(leadHeadCode, "leadHeadCode", methodElement);
                            symmetry = getElementTag(symmetry, "symmetry", methodElement);


                            little = getAtribute(little, "little", methodClasificationNodeList);
                            differential = getAtribute(differential, "differential", methodClasificationNodeList);
                            plain = getAtribute(plain, "plain", methodClasificationNodeList);
                            trebleDodging = getAtribute(trebleDodging, "trebleDodging", methodClasificationNodeList);


                            methods.add(
                                    new Method(
                                            methodId,
                                            Integer.parseInt(stage),
                                            name,
                                            title,
                                            notation,
                                            classification,
                                            Integer.parseInt(lengthOfLead),
                                            Integer.parseInt(numberOfHunts),
                                            huntbellPath,
                                            leadHead,
                                            leadHeadCode,
                                            symmetry,
                                            little,
                                            differential,
                                            plain,
                                            trebleDodging,
                                            calcBob(methodId, notation, Integer.parseInt(stage)),
                                            calcSingle(methodId, notation, Integer.parseInt(stage))
                                    )
                            );
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return methods;
    }

    public String getElementTag(String field, String fieldName, Element element) {
        try {
            field = element.getElementsByTagName(fieldName).item(0).getTextContent();
        } catch (Exception ignored) {
        }
        return field;
    }

    public boolean getAtribute(boolean field, String fieldName, NodeList nodeList) {
        try {
            String li = nodeList.item(0).getAttributes().getNamedItem(fieldName).getTextContent();
            field = li != null;
        } catch (Exception ignored) {
        }
        return field;
    }

    public String getLongNotation(String notationIn) {
        StringBuilder notationOut = new StringBuilder();
        if (notationIn.contains(",")) {
            String[] split = notationIn.split(",");
            for (int i = 0; i < split.length; i++) {
                notationOut.append(symmetryForNotation(decodeNotation(split[i])));
                if (i != split.length - 1) {
                    notationOut.append(".");
                }
            }
        } else {
            notationOut.append(decodeNotation(notationIn));
        }
        return notationOut.toString();
    }

    private String symmetryForNotation(String notation) {
        String[] split = notation.split("\\.");
        StringBuilder notationOut = new StringBuilder();
        for (String s : split) {
            notationOut.append(s);
            if (split.length > 1) {
                notationOut.append(".");
            }
        }
        for (int i = split.length - 2; i >= 0; i--) {
            notationOut.append(split[i]);
            if (i != 0) {
                notationOut.append(".");
            }
        }
        return notationOut.toString();
    }

    private String decodeNotation(String notation) {
        String[] notationArr = notation.split("(?!^)");
        StringBuilder notationOut = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        List<String> returnArr = new ArrayList<>();
        for (String s : notationArr) {
            switch (s) {
                case "-" -> {
                    if (temp.length() > 0) {
                        returnArr.add(temp.toString());
                        temp = new StringBuilder();
                    }
                    returnArr.add("x");
                }
                case "." -> {
                    returnArr.add(temp.toString());
                    temp = new StringBuilder();
                }
                default -> temp.append(s);
            }
        }
        returnArr.add(temp.toString());
        for (int i = 0; i < returnArr.size(); i++) {
            notationOut.append(returnArr.get(i));
            if (i != returnArr.size() - 1) {
                notationOut.append(".");
            }
        }
        return notationOut.toString();
    }

    public String calcBob(String methodId, String notation, int stage) {
        if (stage < 5) {
            return "";
        }
        String finalNot = notation.split("\\.")[notation.split("\\.").length - 1];
        String exceptionOrNull = getExceptionOrNull(methodId, "b");
        String main = "";
        String end = "";
        if (finalNot.length() % 2 != 0) {
            end = finalNot.substring(finalNot.length() - 1);
            finalNot = finalNot.substring(0, finalNot.length() - 1);
        }
        if (exceptionOrNull == null) {
            switch (finalNot) {
                case "12", "16" -> main = "14";
                case "18" -> main = "16";
                case "10" -> main = "18";
                case "1T" -> main = "10";
                case "1B" -> main = "1T";
                case "1D" -> main = "1B";
                case "1G" -> main = "1D";
                case "1J" -> main = "1G";
                case "1L" -> main = "1J";
                case "14" -> {
                    return null;
                }
                default -> {
                    return "";
                }
            }
            return main + end;
        } else {
            return exceptionOrNull;
        }
    }

    private String calcSingle(String methodId, String notation, int stage) {
        if (stage < 5) {
            return "";
        }
        String finalNot = notation.split("\\.")[notation.split("\\.").length - 1];
        String exceptionOrNull = getExceptionOrNull(methodId, "s");
        String main = "";
        String end = "";
        if (finalNot.length() % 2 != 0) {
            end = finalNot.substring(finalNot.length() - 1);
            finalNot = finalNot.substring(0, finalNot.length() - 1);
        }
        if (exceptionOrNull == null) {
            switch (finalNot) {
                case "12" -> main = "1234";
                case "16" -> main = "1456";
                case "18" -> main = "1678";
                case "10" -> main = "1890";
                case "1T" -> main = "10ET";
                case "1B" -> main = "1TAB";
                case "1D" -> main = "1BCD";
                case "1G" -> main = "1DFG";
                case "1J" -> main = "1GHJ";
                case "1L" -> main = "1JKL";
                case "14" -> {
                    return null;
                }
                default -> {
                    return "";
                }
            }
            return main + end;
        } else {
            return exceptionOrNull;
        }
    }

    public String getExceptionOrNull(String methodId, String bobOrSingle) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/callExceptions.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains("//")) {
                    String[] lineSplit = line.replace(" ", "").split(",");
                    if (lineSplit[0].equals(methodId) && lineSplit[1].equals(bobOrSingle)) {
                        return lineSplit[2];
                    }
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
