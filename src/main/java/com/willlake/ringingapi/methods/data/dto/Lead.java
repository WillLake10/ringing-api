package com.willlake.ringingapi.methods.data.dto;

import com.willlake.ringingapi.RingingApiApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lead {
    private List<Row> rows;

    public Lead(List<Row> rows) {
        this.rows = rows;
    }

    public Lead(Row row, String notation) {
        this.rows = new ArrayList<>();
        this.rows.add(row);
        String[] splitNot = notation.split("\\.");
        for (int i = 0; i < splitNot.length; i++) {
            addNextRow(splitNot[i]);
        }
    }

    public Lead() {
        this.rows = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder rowList = new StringBuilder();
        for (int i = 0; i < rows.size(); i++) {
            if (i == rows.size() - 1) {
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

    public void addRow(Row row) {
        this.rows.add(row);
    }

    public void addNextRow(String notation) {
        Bell[] bells = new Bell[this.rows.get(0).getRow().size()];
        int[] wayToGo = new int[this.rows.get(0).getRow().size()];
        if (notation.equals("x")) {
            for (int i = 0; i < this.rows.get(this.rows.size() - 1).getRow().size(); i++) {
                if (this.rows.get(this.rows.size() - 1).getRow().get(i).getBellPos() % 2 == 0) {
                    bells[i + 1] = new Bell(this.rows.get(this.rows.size() - 1).getRow().get(i).getBellNum(), i + 1);
                } else {
                    bells[i - 1] = new Bell(this.rows.get(this.rows.size() - 1).getRow().get(i).getBellNum(), i - 1);
                }
            }
        } else {
            String [] splitNot = notation.split("(?!^)");

            for (int i = 0; i < splitNot.length; i++){
                if (splitNot[i].equals("0")){
                    splitNot[i] = "10";
                }else if (splitNot[i].equals("E")){
                    splitNot[i] = "11";
                }else if (splitNot[i].equals("T")){
                    splitNot[i] = "12";
                }else if (splitNot[i].equals("A")){
                    splitNot[i] = "13";
                }else if (splitNot[i].equals("B")){
                    splitNot[i] = "14";
                }else if (splitNot[i].equals("C")){
                    splitNot[i] = "15";
                }else if (splitNot[i].equals("D")){
                    splitNot[i] = "16";
                }else if (splitNot[i].equals("F")){
                    splitNot[i] = "17";
                }else if (splitNot[i].equals("G")){
                    splitNot[i] = "18";
                }else if (splitNot[i].equals("H")){
                    splitNot[i] = "19";
                }else if (splitNot[i].equals("J")){
                    splitNot[i] = "20";
                }else if (splitNot[i].equals("K")){
                    splitNot[i] = "21";
                }else if (splitNot[i].equals("L")){
                    splitNot[i] = "22";
                }
            }

            boolean normalDirection = true;

            for (int i = 0; i < bells.length; i++){
                if(Arrays.asList(splitNot).contains(String.valueOf(i+1))){
                    wayToGo[i] = STAY;
                    normalDirection = !normalDirection;
                }else {
                    if (this.rows.get(this.rows.size() - 1).getRow().get(i).getBellPos() % 2 != 0){
                        if (normalDirection){
                            wayToGo[i] = DOWN;
                        } else {
                            wayToGo[i] = UP;
                        }
                    } else {
                        if (normalDirection){
                            wayToGo[i] = UP;
                        } else {
                            wayToGo[i] = DOWN;
                        }
                    }
                }
            }
//            log.info(notation);
//            log.info(Arrays.toString(splitNot));
//            log.info(Arrays.toString(wayToGo));

            for (int i = 0; i < bells.length; i++){
                if (wayToGo[i] == STAY){
                    bells[i] = new Bell(this.rows.get(this.rows.size() - 1).getRow().get(i).getBellNum(), i);
                } else if (wayToGo[i] == UP){
                    bells[i+1] = new Bell(this.rows.get(this.rows.size() - 1).getRow().get(i).getBellNum(), i+1);
                } else if (wayToGo[i] == DOWN){
                    bells[i-1] = new Bell(this.rows.get(this.rows.size() - 1).getRow().get(i).getBellNum(), i-1);
                }
            }
        }

        this.rows.add(new Row(bells.length, Arrays.asList(bells)));
    }


    private static final Logger log = LoggerFactory.getLogger(RingingApiApplication.class);

    private static final int UP = 1;
    private static final int DOWN = -1;
    private static final int STAY = 0;
}
