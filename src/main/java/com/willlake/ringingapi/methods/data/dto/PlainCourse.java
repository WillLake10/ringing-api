package com.willlake.ringingapi.methods.data.dto;

import com.willlake.ringingapi.RingingApiApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PlainCourse {
    List<Lead> plainCourse;

    private static final Logger log = LoggerFactory.getLogger(PlainCourse.class);

    public PlainCourse(Method method) {
        this.plainCourse = new ArrayList<>();
        Row row = new Row(method.getStage(), true);
        boolean endOfCourse = false;
        while (!endOfCourse){
            this.plainCourse.add(new Lead(row, method.getNotation()));
            row = this.plainCourse.get(this.plainCourse.size()-1).getRows().get(this.plainCourse.get(this.plainCourse.size()-1).getRows().size()-1);
            log.info(this.plainCourse.get(this.plainCourse.size()-1).getRows().get(this.plainCourse.get(this.plainCourse.size()-1).getRows().size()-1).getShortRow());
            if (this.plainCourse.get(this.plainCourse.size()-1).getRows().get(this.plainCourse.get(this.plainCourse.size()-1).getRows().size()-1).isRounds()){
                endOfCourse = true;
            }
        }
    }

    @Override
    public String toString() {
        return "PlainCourse{" +
                "plainCourse=" + plainCourse +
                '}';
    }

    public void addLead(String notation, Row initialRow) {
        this.plainCourse.add(new Lead(initialRow, notation));
    }

    public List<Lead> getPlainCourse() {
        return plainCourse;
    }

    public void setPlainCourse(List<Lead> plainCourse) {
        this.plainCourse = plainCourse;
    }
}
