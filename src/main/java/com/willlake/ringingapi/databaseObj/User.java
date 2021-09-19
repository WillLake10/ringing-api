package com.willlake.ringingapi.databaseObj;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private String userId;

    private String fName;
    private String sName;

    private String pWordHash;

    protected User(){}

    public User(String userId, String fName, String sName, String pWordHash) {
        this.userId = userId;
        this.fName = fName;
        this.sName = sName;
        this.pWordHash = pWordHash;
    }
}
