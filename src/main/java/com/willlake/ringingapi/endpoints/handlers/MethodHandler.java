package com.willlake.ringingapi.endpoints.handlers;

import com.willlake.ringingapi.endpoints.dto.MethodShort;
import com.willlake.ringingapi.methods.data.dto.Lead;
import com.willlake.ringingapi.databaseObj.Method;
import com.willlake.ringingapi.methods.data.MethodRepository;
import com.willlake.ringingapi.methods.data.dto.PlainCourse;
import com.willlake.ringingapi.methods.data.dto.Row;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MethodHandler {
    private final MethodRepository methodRepository;

    public MethodHandler(MethodRepository methodRepository) {
        this.methodRepository = methodRepository;
    }

    public Iterable<MethodShort> allMethods() {
        Iterable<Method> allMethod = methodRepository.findAll();
        List<MethodShort> allMethodShort = new ArrayList<MethodShort>();
        allMethod.forEach(method -> allMethodShort.add(new MethodShort(method.getMethodId(), method.getStage(),method.getTitle(), method.getClassification())));
        return allMethodShort;
    }

    public Method getMethod(String methodId) {
        return methodRepository.findByMethodId(methodId);
    }

    public PlainCourse plainCourse(String methodId) {
        Method method = methodRepository.findByMethodId(methodId);
        Lead lead = new Lead(new Row(method.getStage(), true), method.getNotation());
        PlainCourse plainCourse = new PlainCourse(method);
        return plainCourse;
    }

    public List<String> plainShort(String methodId) {
        Method method = methodRepository.findByMethodId(methodId);
        Lead lead = new Lead(new Row(method.getStage(), true), method.getNotation());
        PlainCourse plainCourse = new PlainCourse(method);
        List<String> retList = new ArrayList<>();
        for (int j = 0; j < plainCourse.getPlainCourse().size(); j++) {
            for (int i = 0; i < lead.getRows().size(); i++) {
                retList.add(plainCourse.getPlainCourse().get(j).getRows().get(i).getShortRow());
            }
        }
        return retList;
    }
}
