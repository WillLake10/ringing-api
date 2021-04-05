package com.willlake.ringingapi.endpoints;

import com.willlake.ringingapi.methods.data.Method;
import com.willlake.ringingapi.methods.data.MethodRepository;

public class MethodHandler {
    private final MethodRepository methodRepository;

    public MethodHandler(MethodRepository methodRepository) {
        this.methodRepository = methodRepository;
    }

    public Iterable<Method> allMethods() {
        return methodRepository.findAll();
    }
}
