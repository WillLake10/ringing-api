package com.willlake.ringingapi.methods.data.dto;

import javax.persistence.*;

@Entity
public class Method {
    @Id
    private String methodId;

    private int stage;
    private String name;
    private String title;
    @Column(length = 500)
    private String notation;

    private String classification;
    private int lengthOfLead;
    private int numberOfHunts;
    @Column(length = 500)
    private String huntbellPath;
    private String leadHead;
    private String leadHeadCode;
    private String symmetry;
    private boolean little;
    private boolean differential;
    private boolean plain;
    private boolean trebleDodging;


    protected Method() {}

    public Method(String methodId, int stage, String name, String title, String notation, String classification, int lengthOfLead, int numberOfHunts, String huntbellPath, String leadHead, String leadHeadCode, String symmetry, boolean little, boolean differential, boolean plain, boolean trebleDodging) {
        this.methodId = methodId;
        this.stage = stage;
        this.name = name;
        this.title = title;
        this.notation = notation;
        this.classification = classification;
        this.lengthOfLead = lengthOfLead;
        this.numberOfHunts = numberOfHunts;
        this.huntbellPath = huntbellPath;
        this.leadHead = leadHead;
        this.leadHeadCode = leadHeadCode;
        this.symmetry = symmetry;
        this.little = little;
        this.differential = differential;
        this.plain = plain;
        this.trebleDodging = trebleDodging;
    }

    @Override
    public String toString() {
        return "Method{" +
                "methodId='" + methodId + '\'' +
                ", stage=" + stage +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", notation='" + notation + '\'' +
                ", classification='" + classification + '\'' +
                ", lengthOfLead=" + lengthOfLead +
                ", numberOfHunts=" + numberOfHunts +
                ", huntbellPath='" + huntbellPath + '\'' +
                ", leadHead='" + leadHead + '\'' +
                ", leadHeadCode='" + leadHeadCode + '\'' +
                ", symmetry='" + symmetry + '\'' +
                ", little=" + little +
                ", differential=" + differential +
                ", plain=" + plain +
                ", trebleDodging=" + trebleDodging +
                '}';
    }

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotation() {
        return notation;
    }

    public void setNotation(String notation) {
        this.notation = notation;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public int getLengthOfLead() {
        return lengthOfLead;
    }

    public void setLengthOfLead(int lengthOfLead) {
        this.lengthOfLead = lengthOfLead;
    }

    public int getNumberOfHunts() {
        return numberOfHunts;
    }

    public void setNumberOfHunts(int numberOfHunts) {
        this.numberOfHunts = numberOfHunts;
    }

    public String getHuntbellPath() {
        return huntbellPath;
    }

    public void setHuntbellPath(String huntbellPath) {
        this.huntbellPath = huntbellPath;
    }

    public String getLeadHead() {
        return leadHead;
    }

    public void setLeadHead(String leadHead) {
        this.leadHead = leadHead;
    }

    public String getLeadHeadCode() {
        return leadHeadCode;
    }

    public void setLeadHeadCode(String leadHeadCode) {
        this.leadHeadCode = leadHeadCode;
    }

    public String getSymmetry() {
        return symmetry;
    }

    public void setSymmetry(String symmetry) {
        this.symmetry = symmetry;
    }

    public boolean isLittle() {
        return little;
    }

    public void setLittle(boolean little) {
        this.little = little;
    }

    public boolean isDifferential() {
        return differential;
    }

    public void setDifferential(boolean differential) {
        this.differential = differential;
    }

    public boolean isPlain() {
        return plain;
    }

    public void setPlain(boolean plain) {
        this.plain = plain;
    }

    public boolean isTrebleDodging() {
        return trebleDodging;
    }

    public void setTrebleDodging(boolean trebleDodging) {
        this.trebleDodging = trebleDodging;
    }
}
