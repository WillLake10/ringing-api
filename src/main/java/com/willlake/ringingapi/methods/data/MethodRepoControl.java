package com.willlake.ringingapi.methods.data;

import com.willlake.ringingapi.methods.data.dto.Method;

import java.util.HashSet;
import java.util.Set;

public class MethodRepoControl {
    private final MethodRepository repository;
    private final ReadMethodXml readMethodXml;

    public MethodRepoControl(MethodRepository repository, ReadMethodXml readMethodXml) {
        this.repository = repository;
        this.readMethodXml = readMethodXml;
    }

    public void addMethodsFromFile() {
        Set<Method> returnSet = new HashSet<>(readMethodXml.readXml());
        returnSet.forEach( m -> {
                    System.out.println(m);
                    repository.save(m);
                }
        );
    }

    public void clearAllMethodsFromDB() {
        repository.deleteAll();
    }
}
