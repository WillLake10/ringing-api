package com.willlake.ringingapi.databaseObj;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RingerId implements Serializable {
    private String performanceId;
    private String bell;

    protected  RingerId(){}
    public RingerId(String performanceId, String bell) {
        this.performanceId = performanceId;
        this.bell = bell;
    }

    @Override
    public String toString() {
        return "RingerId{" +
                "performanceId='" + performanceId + '\'' +
                ", bell='" + bell + '\'' +
                '}';
    }
}
