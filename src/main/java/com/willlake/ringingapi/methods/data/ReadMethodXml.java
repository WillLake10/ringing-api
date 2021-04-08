package com.willlake.ringingapi.methods.data;

import com.willlake.ringingapi.methods.data.dto.Method;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
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

                    String methodId = "";
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
                            notation = getElementTag(notation, "notation", methodElement);
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
                                            trebleDodging
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

}
