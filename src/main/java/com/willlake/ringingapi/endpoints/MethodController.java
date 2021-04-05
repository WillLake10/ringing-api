package com.willlake.ringingapi.endpoints;

import com.willlake.ringingapi.methods.data.Method;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

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
