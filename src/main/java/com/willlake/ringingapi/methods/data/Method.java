package com.willlake.ringingapi.methods.data;

import javax.persistence.*;

@Entity
public class Method {
    @Id
    private long methodId;
    private String name;
    private String firstRung;
    private int stage;
    private String symmetry;
    private Boolean little;
    private int lengthOfLead;
    private String leadHead;
    @Column(length = 500)
    private String notation;

    protected Method() {}

    public Method(long methodId, String name, String firstRung, int stage, String symmetry, Boolean little, int lengthOfLead, String leadHead, String notation) {
        this.methodId = methodId;
        this.name = name;
        this.firstRung = firstRung;
        this.stage = stage;
        this.symmetry = symmetry;
        this.little = little;
        this.lengthOfLead = lengthOfLead;
        this.leadHead = leadHead;
        this.notation = notation;
    }

    @Override
    public String toString() {
        return "Method{" +
                "methodId=" + methodId +
                ", name='" + name + '\'' +
                ", firstRung='" + firstRung + '\'' +
                ", stage=" + stage +
                ", symmetry='" + symmetry + '\'' +
                ", little=" + little +
                ", lengthOfLead=" + lengthOfLead +
                ", leadHead='" + leadHead + '\'' +
                ", notation='" + notation + '\'' +
                '}';
    }

    public long getMethodId() {
        return methodId;
    }

    public String getName() {
        return name;
    }

    public String getFirstRung() {
        return firstRung;
    }

    public int getStage() {
        return stage;
    }

    public String getSymmetry() {
        return symmetry;
    }

    public Boolean getLittle() {
        return little;
    }

    public int getLengthOfLead() {
        return lengthOfLead;
    }

    public String getLeadHead() {
        return leadHead;
    }

    public String getNotation() {
        return notation;
    }
}
