package com.willlake.ringingapi.methods.data.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Row {
    int numberOfBells;
    boolean isRounds;
    List<Bell> row;

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
        this.isRounds = false;
    }

    public Row(int numberOfBells, Bell[] row) {
        this.numberOfBells = numberOfBells;
        this.row = new ArrayList<>();
        this.row.addAll(Arrays.asList(row));
        this.isRounds = false;
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
            retString.append(row.get(i).bell);
        }
        return retString.toString();
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
