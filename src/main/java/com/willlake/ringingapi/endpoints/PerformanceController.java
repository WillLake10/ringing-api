package com.willlake.ringingapi.endpoints;

import com.willlake.ringingapi.endpoints.handlers.PerformanceHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/performance")
public class PerformanceController {
    private final PerformanceHandler performanceHandler;

    public PerformanceController(PerformanceHandler performanceHandler) {
        this.performanceHandler = performanceHandler;
    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> add(@PathVariable String id) {
        return performanceHandler.add(id);
    }
}