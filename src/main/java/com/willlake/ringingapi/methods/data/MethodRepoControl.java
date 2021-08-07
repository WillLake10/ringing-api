package com.willlake.ringingapi.methods.data;

import com.willlake.ringingapi.RingingApiApplication;
import com.willlake.ringingapi.databaseObj.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class MethodRepoControl {
    private static final Logger log = LoggerFactory.getLogger(MethodRepoControl.class);

    private final MethodRepository repository;
    private final ReadMethodXml readMethodXml;

    public MethodRepoControl(MethodRepository repository, ReadMethodXml readMethodXml) {
        this.repository = repository;
        this.readMethodXml = readMethodXml;
    }

    public void addMethodsFromFile() {
        log.info("Adding Methods to database");
        Set<Method> returnSet = new HashSet<>(readMethodXml.readXml());
        repository.saveAll(returnSet);
        log.info("Added " + (long) returnSet.size() + " Methods to database");
    }

    public void clearAllMethodsFromDB() {
        repository.deleteAll();
    }
}
