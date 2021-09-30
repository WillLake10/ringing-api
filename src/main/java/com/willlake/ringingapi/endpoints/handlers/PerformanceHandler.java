package com.willlake.ringingapi.endpoints.handlers;

import com.willlake.ringingapi.performances.PerformanceIngest;
import com.willlake.ringingapi.utils.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PerformanceHandler {
    private final PerformanceIngest performanceIngest;

    public PerformanceHandler(PerformanceIngest performanceIngest) {
        this.performanceIngest = performanceIngest;
    }

    public ResponseEntity<String> add(String id) {
        performanceIngest.addPerformanceToDatabase(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    public ResponseEntity<String> addFromSearch(String search) {
        Status status = performanceIngest.addPerformancesFromSearch(search);
        return new ResponseEntity<>(status.getMessage(), status.getHttpStatus());
    }
}
