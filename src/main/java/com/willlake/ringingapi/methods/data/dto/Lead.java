package com.willlake.ringingapi.methods.data.dto;

import java.util.ArrayList;
import java.util.List;

public class Lead {
    List<Row> rows;

    public Lead(List<Row> rows) {
        this.rows = rows;
    }

    public Lead() {
        this.rows = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder rowList = new StringBuilder();
        for (int i = 0; i < rows.size(); i++){
            if (i == rows.size()-1){
                rowList.append(rows.get(i).toShortString());
            } else {
                rowList.append(rows.get(i).toShortString()).append(", ");
            }
        }
        return "Lead{" +
                "rows=" + rowList.toString() +
                '}';
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public void addRow(Row row){
        this.rows.add(row);
    }

    public void addNextRow(String notation){
        Bell[] bells = new Bell[this.rows.get(0).row.size()];
        if (notation.equals("x")){
            for (int i = 0; i < this.rows.get(this.rows.size()-1).row.size(); i++){
                if(this.rows.get(this.rows.size()-1).row.get(i).bellNum % 2 == 0){
                    bells[i-1] = new Bell(this.rows.get(this.rows.size()-1).row.get(i).bellNum, i-1);
                } else {
                    bells[i+1] = new Bell(this.rows.get(this.rows.size()-1).row.get(i).bellNum, i+1);
                }
            }
        }

        for (int i = 0; i < bells.length; i++){
            this.rows.add(new Row(bells.length, bells));
        }
    }
}
