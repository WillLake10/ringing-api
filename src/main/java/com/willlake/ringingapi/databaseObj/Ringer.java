package com.willlake.ringingapi.databaseObj;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ringer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ringerId;

    private String performanceId;

    private String bell;
    private String name;

    protected Ringer(){}

    public Ringer(String performanceId, String bell, String name) {
        this.performanceId = performanceId;
        this.bell = bell;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Ringer{" +
                "ringerId=" + ringerId +
                ", performanceId='" + performanceId + '\'' +
                ", bell='" + bell + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
