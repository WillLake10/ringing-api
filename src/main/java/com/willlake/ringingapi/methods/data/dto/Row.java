package com.willlake.ringingapi.methods.data.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Row {
    private int numberOfBells;
    private boolean isRounds;
    private List<Bell> row;
    private String shortRow;

    public Row(int numberOfBells, boolean isRounds) {
        this.numberOfBells = numberOfBells;
        this.isRounds = isRounds;
        this.row = new ArrayList<Bell>();
        for (int i = 1; i <= numberOfBells; i++) {
            this.row.add(new Bell(i, i - 1));
        }
        this.shortRow = findShortRowString();
    }

    public Row(int numberOfBells, List<Bell> row) {
        this.numberOfBells = numberOfBells;
        this.row = row;
        this.isRounds = checkIfRounds();
        this.shortRow = findShortRowString();
    }

    @Override
    public String toString() {
        return "Row{" +
                "numberOfBells=" + numberOfBells +
                ", isRounds=" + isRounds +
                ", shortRow='" + shortRow + '\'' +
                ", row=" + row +
                '}';
    }

    public String toShortString() {
        return "Row{" +
                "numberOfBells=" + numberOfBells +
                ", isRounds=" + isRounds +
                ", row=" + shortRow +
                '}';
    }

    private String findShortRowString() {
        StringBuilder retString = new StringBuilder();
        for (int i = 0; i < row.size(); i++) {
            int finalI = i;
            retString.append(row.stream()
                    .filter(bell -> bell.getBellPos() == finalI)
                    .collect(Collectors.toList())
                    .get(0).getBell());
        }
        return retString.toString();
    }

    public boolean checkIfRounds() {
        for (int i = 0; i < row.size(); i++) {
            if (row.get(i).getBellPos() + 1 != row.get(i).getBellNum()) {
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

    public String getShortRow() {
        return shortRow;
    }

    public void setShortRow(String shortRow) {
        this.shortRow = shortRow;
    }
}
