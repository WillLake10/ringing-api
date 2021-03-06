package com.willlake.ringingapi.methods.data.dto;

import java.util.Arrays;

public class Bell {
    private String[] bellNames = {
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "0",
            "E",
            "T",
            "A",
            "B",
            "C",
            "D",
            "F",
            "G",
            "H",
            "J",
            "K",
            "L"
    };

    private String bell;
    private int bellNum;
    private int bellPos;

    public Bell(int bellNum, int bellPos) {
        this.bell = bellNames[bellNum-1];
        this.bellPos = bellPos;
        this.bellNum = bellNum;
    }

    @Override
    public String toString() {
        return "Bell{" +
                "bell='" + bell + '\'' +
                ", bellNum=" + bellNum +
                ", bellPos=" + bellPos +
                '}';
    }

    public String getBell() {
        return bell;
    }

    public void setBell(String bell) {
        this.bell = bell;
    }

    public int getBellPos() {
        return bellPos;
    }

    public void setBellPos(int bellPos) {
        this.bellPos = bellPos;
    }

    public int getBellNum() {
        return bellNum;
    }

    public void setBellNum(int bellNum) {
        this.bellNum = bellNum;
    }
}
