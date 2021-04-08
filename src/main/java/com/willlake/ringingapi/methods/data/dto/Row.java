package com.willlake.ringingapi.methods.data.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Row {
    private int numberOfBells;
    private boolean isRounds;
    private List<Bell> row;

    public Row(int numberOfBells, boolean isRounds) {
        this.numberOfBells = numberOfBells;
        this.isRounds = isRounds;
        this.row = new ArrayList<Bell>();
        for (int i = 1; i <= numberOfBells; i++){
            this.row.add(new Bell(i, i-1));
        }
    }

    public Row(int numberOfBells, List<Bell> row) {
        this.numberOfBells = numberOfBells;
        this.row = row;
        this.isRounds = checkIfRounds();
    }

    @Override
    public String toString() {
        return "Row{" +
                "numberOfBells=" + numberOfBells +
                ", isRounds=" + isRounds +
                ", row=" + row +
                '}';
    }

    public String toShortString() {
        StringBuilder retString = new StringBuilder();
        for(int i = 0; i < row.size(); i++){
            retString.append(row.get(i).getBell());
        }
        return retString.toString();
    }

    public boolean checkIfRounds() {
        for (int i = 0; i < row.size(); i++){
            if (row.get(i).getBellPos() != row.get(i).getBellNum()){
                return false;
            }
        }
        return true;
    }

    public int getNumberOfBells() {
        return numberOfBells;
    }

    public void setNumberOfBells(int numberOfBells) {
        this.numberOfBells = numberOfBells;
    }

    public boolean isRounds() {
        return isRounds;
    }

    public void setRounds(boolean rounds) {
        isRounds = rounds;
    }

    public List<Bell> getRow() {
        return row;
    }

    public void setRow(List<Bell> row) {
        this.row = row;
    }
}
