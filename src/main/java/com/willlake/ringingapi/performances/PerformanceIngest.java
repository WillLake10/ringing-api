package com.willlake.ringingapi.performances;

import com.willlake.ringingapi.databaseObj.Performance;
import com.willlake.ringingapi.endpoints.handlers.DatabaseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

public class PerformanceIngest {
    private static final Logger log = LoggerFactory.getLogger(PerformanceIngest.class);

    private final PerformancesRequester performancesRequester;

    public PerformanceIngest(PerformancesRequester performancesRequester) {
        this.performancesRequester = performancesRequester;
    }

    public void addPerformanceToDatabase(String id) {
        String performanceXml = performancesRequester.getPerformance(id);
        if (performanceXml != null){
            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();

                Document doc = db.parse(new InputSource(new StringReader(performanceXml)));

                NodeList placeNodeList = doc.getElementsByTagName("place");
                Element placeElement = (Element) placeNodeList.item(0);

                NodeList titleNodeList = doc.getElementsByTagName("title");
                Element titleElement = (Element) titleNodeList.item(0);

                String association = "";
                String towerBaseId = "";
                String place = "";
                String dedication = "";
                String county = "";
                String type = "";
                String tenor = "";
                String date = "";
                String duration = "";
                String changes = "";
                String method = "";
                String details = "";

                association = getElementTagDoc(association, "association", doc);
                towerBaseId = doc.getElementsByTagName("place").item(0).getAttributes().getNamedItem("towerbase-id").getTextContent();
                place = "";
                dedication = "";
                county = "";
                for (int i = 0; i <= 3; i++){
                    try {
                        place = placeElement.getElementsByTagName("place-name").item(i).getAttributes().getNamedItem("place").getTextContent();
                    } catch (Exception ignored) {}
                    try {
                        dedication = placeElement.getElementsByTagName("place-name").item(i).getAttributes().getNamedItem("dedication").getTextContent();
                    } catch (Exception ignored) {}
                    try {
                        county = placeElement.getElementsByTagName("place-name").item(i).getAttributes().getNamedItem("county").getTextContent();
                    } catch (Exception ignored) {}
                }
                type = placeElement.getElementsByTagName("ring").item(0).getAttributes().getNamedItem("type").getTextContent();
                tenor = placeElement.getElementsByTagName("ring").item(0).getAttributes().getNamedItem("tenor").getTextContent();
                date = getElementTagDoc(date, "date", doc);
                duration = getElementTagDoc(duration, "duration", doc);
                changes = getElementTag(changes, "changes", titleElement);
                method = getElementTag(method, "method", titleElement);
                details = getElementTagDoc(details, "details", doc);

                NodeList footnotesNodeList = doc.getElementsByTagName("footnote");
                String footnote = "";

                Performance performance = new Performance(id, association, towerBaseId, place, dedication, county, type, tenor, date, duration, changes, method, details, footnote);

                log.info(performance.toString());
//                log.info(String.valueOf(doc.getElementsByTagName("date").getLength()));

//                NodeList performanceNodeList = doc.getElementsByTagName("date");

//                Node perfNode = performanceNodeList.item(0);

                log.info(doc.getElementsByTagName("date").item(0).getTextContent());
            } catch (ParserConfigurationException | IOException | SAXException e) {
                e.printStackTrace();
            }
        }
    }

    public String getElementTag(String field, String fieldName, Element element) {
        try {
            field = element.getElementsByTagName(fieldName).item(0).getTextContent();
        } catch (Exception ignored) {
        }
        return field;
    }

    public String getElementTagDoc(String field, String fieldName, Document element) {
        try {
            field = element.getElementsByTagName(fieldName).item(0).getTextContent();
        } catch (Exception ignored) {
        }
        return field;
    }

}
