package com.willlake.ringingapi.endpoints.handlers;

import com.willlake.ringingapi.methods.data.dto.Lead;
import com.willlake.ringingapi.methods.data.dto.Method;
import com.willlake.ringingapi.methods.data.MethodRepository;
import com.willlake.ringingapi.methods.data.dto.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MethodHandler {
    private final MethodRepository methodRepository;

    public MethodHandler(MethodRepository methodRepository) {
        this.methodRepository = methodRepository;
    }

    public Iterable<Method> allMethods() {
        return methodRepository.findAll();
    }

    public Method getMethod(String methodId) {
        return methodRepository.findByMethodId(methodId);
    }

    public Lead plainCourse(String methodId) {
        Method method = methodRepository.findByMethodId(methodId);
        Lead lead = new Lead(new Row(method.getStage(), true), method.getNotation());
        return lead;
    }

    public List<String> plainShort(String methodId) {
        Method method = methodRepository.findByMethodId(methodId);
        Lead lead = new Lead(new Row(method.getStage(), true), method.getNotation());
        List<String> retList = new ArrayList<>();
        for (int i = 0; i < lead.getRows().size(); i++) {
            retList.add(lead.getRows().get(i).toShortString());
        }
        return retList;
    }
}
