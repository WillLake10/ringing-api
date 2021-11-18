package com.willlake.ringingapi.endpoints.dto;

public class MethodShort {
    private String methodId;
    private int stage;
    private String title;
    private String classification;

    public MethodShort(String methodId, int stage, String title, String classification) {
        this.methodId = methodId;
        this.stage = stage;
        this.title = title;
        this.classification = classification;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }
}
