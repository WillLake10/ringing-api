package com.willlake.ringingapi.performances;

import com.willlake.ringingapi.databaseObj.Performance;
import com.willlake.ringingapi.databaseObj.Ringer;
import com.willlake.ringingapi.databaseObj.RingerId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PerformanceIngest {
    private static final Logger log = LoggerFactory.getLogger(PerformanceIngest.class);

    private final PerformancesRequester performancesRequester;
    private final PerformanceRepository performanceRepository;
    private final  RingerRepository ringerRepository;

    public PerformanceIngest(PerformancesRequester performancesRequester, PerformanceRepository performanceRepository, RingerRepository ringerRepository) {
        this.performancesRequester = performancesRequester;
        this.performanceRepository = performanceRepository;
        this.ringerRepository = ringerRepository;
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

                NodeList ringersNodeList = doc.getElementsByTagName("ringers");
                Element ringersElement = (Element) ringersNodeList.item(0);

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

                int footnoteLength = doc.getElementsByTagName("footnote").getLength();
                ArrayList<String> footnotes = new ArrayList<>();
                for (int i = 0; i < footnoteLength; i++){
                    footnotes.add(doc.getElementsByTagName("footnote").item(i).getTextContent());
                }

                int ringerLength = ringersElement.getElementsByTagName("ringer").getLength();
                for (int i = 0; i < ringerLength; i++){
                    boolean conductor = false;
                    try {
                        if (Objects.equals(ringersElement.getElementsByTagName("ringer").item(i).getAttributes().getNamedItem("conductor").getTextContent(), "true")) {
                            conductor = true;
                        }
                    } catch (Exception ignored) {}

                    ringerRepository.save(new Ringer(new RingerId(id, ringersElement.getElementsByTagName("ringer").item(i).getAttributes().getNamedItem("bell").getTextContent()), ringersElement.getElementsByTagName("ringer").item(i).getTextContent(), conductor));
                }

                Performance performance = new Performance(id, association, towerBaseId, place, dedication, county, type, tenor, date, duration, changes, method, details, footnotes.toString());

                performanceRepository.save(performance);
                log.info("Performance added to database: " + performance);
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
