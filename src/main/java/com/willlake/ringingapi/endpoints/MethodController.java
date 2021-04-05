package com.willlake.ringingapi.endpoints;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/methods")
public class MethodController {
    private final MethodHandler methodHandler;

    public MethodController(MethodHandler methodHandler) {
        this.methodHandler = methodHandler;
    }
}
