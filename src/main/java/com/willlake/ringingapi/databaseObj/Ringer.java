package com.willlake.ringingapi.databaseObj;

import javax.persistence.*;

@Entity
public class Ringer {
    @EmbeddedId
    private RingerId ringerId;

    private String name;
    private String userId;

    protected Ringer(){}

    public Ringer(RingerId ringerId, String name) {
        this.ringerId = ringerId;
        this.name = name;
        this.userId = "";
    }

    @Override
    public String toString() {
        return "Ringer{" +
                "ringerId=" + ringerId +
                ", userId='" + userId + '\'' +
                '}';
    }
}
