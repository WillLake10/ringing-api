package com.willlake.ringingapi.databaseObj;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Ringer implements Serializable {
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
                ", name='" + name + '\'' +
                ", conductor=" + conductor +
                ", userId='" + userId + '\'' +
                '}';
    }
}
