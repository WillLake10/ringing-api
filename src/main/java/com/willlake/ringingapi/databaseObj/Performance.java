package com.willlake.ringingapi.databaseObj;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Entity
public class Performance {
    @Id
    private String performanceID;

    private String association;

    private String towerBaseId;
    private String place;
    private String dedication;
    private String county;

    private String type;
    private String tenor;

    private String date;

    private String duration;
    private String changes;
    private String method;

    private String details;

    private String footnotes;

    protected Performance(){}

    public Performance(String performanceID, String association, String towerBaseId, String place, String dedication, String county, String type, String tenor, String date, String duration, String changes, String method, String details, String footnotes) {
        this.performanceID = performanceID;
        this.association = association;
        this.towerBaseId = towerBaseId;
        this.place = place;
        this.dedication = dedication;
        this.county = county;
        this.type = type;
        this.tenor = tenor;
        this.date = date;
        this.duration = duration;
        this.changes = changes;
        this.method = method;
        this.details = details;
        this.footnotes = footnotes;
    }

    @Override
    public String toString() {
        return "Performance{" +
                "performanceID='" + performanceID + '\'' +
                ", association='" + association + '\'' +
                ", towerBaseId='" + towerBaseId + '\'' +
                ", place='" + place + '\'' +
                ", dedication='" + dedication + '\'' +
                ", county='" + county + '\'' +
                ", type='" + type + '\'' +
                ", tenor='" + tenor + '\'' +
                ", date=" + date +
                ", duration='" + duration + '\'' +
                ", changes='" + changes + '\'' +
                ", method='" + method + '\'' +
                ", details='" + details + '\'' +
                ", footnotes='" + footnotes + '\'' +
                '}';
    }
}
