package com.willlake.ringingapi.performances;

import com.willlake.ringingapi.databaseObj.Performance;
import com.willlake.ringingapi.databaseObj.Ringer;
import com.willlake.ringingapi.databaseObj.RingerId;
import com.willlake.ringingapi.utils.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
import java.util.Objects;

public class PerformanceIngest {
    private static final Logger log = LoggerFactory.getLogger(PerformanceIngest.class);

    private final PerformancesRequester performancesRequester;
    private final PerformanceRepository performanceRepository;
    private final RingerRepository ringerRepository;

    public PerformanceIngest(PerformancesRequester performancesRequester, PerformanceRepository performanceRepository, RingerRepository ringerRepository) {
        this.performancesRequester = performancesRequester;
        this.performanceRepository = performanceRepository;
        this.ringerRepository = ringerRepository;
    }

    public Status addPerformanceToDatabase(String id) {
        String performanceXml = performancesRequester.getPerformance(id);
        if (performanceXml != null) {
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

                try {
                    association = getElementTagDoc(association, "association", doc);
                } catch (Exception ignored) {
                    log.warn("Performance " + id + " missing association");
                }
                try {
                    towerBaseId = doc.getElementsByTagName("place").item(0).getAttributes().getNamedItem("towerbase-id").getTextContent();
                } catch (Exception ignored) {
                    log.warn("Performance " + id + " missing towerBaseId");
                }
                place = "";
                dedication = "";
                county = "";
                for (int i = 0; i <= 3; i++) {
                    try {
                        place = placeElement.getElementsByTagName("place-name").item(i).getAttributes().getNamedItem("place").getTextContent();
                    } catch (Exception ignored) {
                    }
                    try {
                        dedication = placeElement.getElementsByTagName("place-name").item(i).getAttributes().getNamedItem("dedication").getTextContent();
                    } catch (Exception ignored) {
                    }
                    try {
                        county = placeElement.getElementsByTagName("place-name").item(i).getAttributes().getNamedItem("county").getTextContent();
                    } catch (Exception ignored) {
                    }
                }
                try {
                    type = placeElement.getElementsByTagName("ring").item(0).getAttributes().getNamedItem("type").getTextContent();
                } catch (Exception ignored) {
                    log.warn("Performance " + id + " missing type");
                }
                try {
                    tenor = placeElement.getElementsByTagName("ring").item(0).getAttributes().getNamedItem("tenor").getTextContent();
                } catch (Exception ignored) {
                    log.warn("Performance " + id + " missing tenor");
                }
                try {
                    date = getElementTagDoc(date, "date", doc);
                } catch (Exception ignored) {
                    log.warn("Performance " + id + " missing date");
                }
                try {
                    duration = getElementTagDoc(duration, "duration", doc);
                } catch (Exception ignored) {
                    log.warn("Performance " + id + " missing duration");
                }
                try {
                    changes = getElementTag(changes, "changes", titleElement);
                } catch (Exception ignored) {
                    log.warn("Performance " + id + " missing changes");
                }
                try {
                    method = getElementTag(method, "method", titleElement);
                } catch (Exception ignored) {
                    log.warn("Performance " + id + " missing method");
                }
                try {
                    details = getElementTagDoc(details, "details", doc);
                } catch (Exception ignored) {
                    log.warn("Performance " + id + " missing details");
                }


                ArrayList<String> footnotes = new ArrayList<>();
                try {
                    int footnoteLength = doc.getElementsByTagName("footnote").getLength();
                    for (int i = 0; i < footnoteLength; i++) {
                        footnotes.add(doc.getElementsByTagName("footnote").item(i).getTextContent());
                    }
                } catch (Exception ignored) {
                    log.warn("Performance " + id + " missing footnotes");
                }

                try {
                    int ringerLength = ringersElement.getElementsByTagName("ringer").getLength();
                    for (int i = 0; i < ringerLength; i++) {
                        boolean conductor = false;
                        try {
                            if (Objects.equals(ringersElement.getElementsByTagName("ringer").item(i).getAttributes().getNamedItem("conductor").getTextContent(), "true")) {
                                conductor = true;
                            }
                        } catch (Exception ignored) {
                        }

                        ringerRepository.save(new Ringer(new RingerId(id, ringersElement.getElementsByTagName("ringer").item(i).getAttributes().getNamedItem("bell").getTextContent()), ringersElement.getElementsByTagName("ringer").item(i).getTextContent(), conductor));
                    }
                } catch (Exception ignored) {
                    log.warn("Performance " + id + " missing ringers");
                }

                Performance performance = new Performance(id, association, towerBaseId, place, dedication, county, type, tenor, date, duration, changes, method, details, footnotes.toString());

                performanceRepository.save(performance);
                log.info("Performance added to database: " + performance);
                return new Status(HttpStatus.OK, "Successfully added performance: " + id);
            } catch (ParserConfigurationException | IOException | SAXException e) {
                log.warn("Something went wrong adding performance: " + id, e);
                return new Status(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong adding performance: " + id);
            }
        }
        return new Status(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong adding performance: " + id);
    }

    private String getElementTag(String field, String fieldName, Element element) {
        try {
            field = element.getElementsByTagName(fieldName).item(0).getTextContent();
        } catch (Exception ignored) {
        }
        return field;
    }

    private String getElementTagDoc(String field, String fieldName, Document element) {
        try {
            field = element.getElementsByTagName(fieldName).item(0).getTextContent();
        } catch (Exception ignored) {
        }
        return field;
    }

    public Status addPerformancesFromSearch(String searchString) {
        String searchXml = performancesRequester.searchPerformance(searchString);
        if (searchXml != null) {
            try {

                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();

                Document doc = db.parse(new InputSource(new StringReader(searchXml)));

                int performanceLength = doc.getElementsByTagName("performance").getLength();
                for (int i = 0; i < performanceLength; i++) {
                    String id = doc.getElementsByTagName("performance").item(i).getAttributes().getNamedItem("href").getTextContent().replace("view.php?id=", "");
                    log.info(i + "/" + performanceLength + " added to database");
                    addPerformanceToDatabase(id);
                }
                log.info(performanceLength + " performances");
            } catch (ParserConfigurationException | IOException | SAXException e) {
                log.warn("Something went wrong searching for performances with search: " + searchString, e);
                return new Status(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong searching for performances with search: " + searchString);
            }
        }
        return new Status(HttpStatus.INTERNAL_SERVER_ERROR, "No performances to be added from search: " + searchString);
    }
}
