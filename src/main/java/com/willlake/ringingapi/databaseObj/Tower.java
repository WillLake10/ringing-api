package com.willlake.ringingapi.databaseObj;

import com.willlake.ringingapi.towers.data.dto.RingType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tower {
    @Id
    private String towerId;

    private RingType ringType;
    private String place1;
    private String place2;
    private String dedication;
    private String county;
    private String country;
    private String diocese;
    private String latitude;
    private String longitude;

    private int bells;
    private int weight;
    private String note;
    private String hz;

    private boolean toilet;
    private boolean simulator;

    @Column(length = 500)
    private String extraInfo;

    private String postcode;
    private String practice;

    protected Tower() {
    }

    public Tower(String towerId, RingType ringType, String place1, String place2, String dedication, String county, String country, String diocese, String latitude, String longitude, int bells, int weight, String note, String hz, Boolean toilet, Boolean simulator, String extraInfo, String postcode, String practice) {
        this.towerId = towerId;
        this.ringType = ringType;
        this.place1 = place1;
        this.place2 = place2;
        this.dedication = dedication;
        this.county = county;
        this.country = country;
        this.diocese = diocese;
        this.latitude = latitude;
        this.longitude = longitude;
        this.bells = bells;
        this.weight = weight;
        this.note = note;
        this.hz = hz;
        this.toilet = toilet;
        this.simulator = simulator;
        this.extraInfo = extraInfo;
        this.postcode = postcode;
        this.practice = practice;
    }

    @Override
    public String toString() {
        return "Tower{" +
                "towerId='" + towerId + '\'' +
                ", ringType=" + ringType +
                ", place1='" + place1 + '\'' +
                ", place2='" + place2 + '\'' +
                ", dedication='" + dedication + '\'' +
                ", county='" + county + '\'' +
                ", country='" + country + '\'' +
                ", diocese='" + diocese + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", bells=" + bells +
                ", weight=" + weight +
                ", note='" + note + '\'' +
                ", hz=" + hz +
                ", toilet=" + toilet +
                ", simulator=" + simulator +
                ", extraInfo='" + extraInfo + '\'' +
                ", postcode='" + postcode + '\'' +
                ", practice='" + practice + '\'' +
                '}';
    }

    public String getTowerId() {
        return towerId;
    }

    public void setTowerId(String towerId) {
        this.towerId = towerId;
    }

    public RingType getRingType() {
        return ringType;
    }

    public void setRingType(RingType ringType) {
        this.ringType = ringType;
    }

    public String getPlace1() {
        return place1;
    }

    public void setPlace1(String place1) {
        this.place1 = place1;
    }

    public String getPlace2() {
        return place2;
    }

    public void setPlace2(String place2) {
        this.place2 = place2;
    }

    public String getDedication() {
        return dedication;
    }

    public void setDedication(String dedication) {
        this.dedication = dedication;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDiocese() {
        return diocese;
    }

    public void setDiocese(String diocese) {
        this.diocese = diocese;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getBells() {
        return bells;
    }

    public void setBells(int bells) {
        this.bells = bells;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getHz() {
        return hz;
    }

    public void setHz(String hz) {
        this.hz = hz;
    }

    public boolean getToilet() {
        return toilet;
    }

    public void setToilet(boolean toilet) {
        this.toilet = toilet;
    }

    public boolean getSimulator() {
        return simulator;
    }

    public void setSimulator(boolean simulator) {
        this.simulator = simulator;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPractice() {
        return practice;
    }

    public void setPractice(String practice) {
        this.practice = practice;
    }
}
