package com.willlake.ringingapi.endpoints.handlers;

import com.willlake.ringingapi.endpoints.MethodHandler;
import com.willlake.ringingapi.methods.data.dto.Method;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/methods")
public class MethodController {
    private final MethodHandler methodHandler;

    public MethodController(MethodHandler methodHandler) {
        this.methodHandler = methodHandler;
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Iterable<Method> allMethods() {
        return methodHandler.allMethods();
    }
}
