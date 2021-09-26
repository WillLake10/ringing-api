package com.willlake.ringingapi.databaseObj;

import javax.persistence.*;

@Entity
public class Ringer {
    @EmbeddedId
    private RingerId ringerId;

    private String name;
    private boolean conductor;
    private String userId;

    protected Ringer(){}

    public Ringer(RingerId ringerId, String name, boolean conductor) {
        this.ringerId = ringerId;
        this.name = name;
        this.conductor = conductor;
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
