package com.willlake.ringingapi.performances;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PerformanceRepoControl {
    private static final Logger log = LoggerFactory.getLogger(PerformanceRepoControl.class);

    private final PerformanceRepository performanceRepository;
    private final PerformanceIngest performanceIngest;

    public PerformanceRepoControl(PerformanceRepository performanceRepository, PerformanceIngest performanceIngest) {
        this.performanceRepository = performanceRepository;
        this.performanceIngest = performanceIngest;
    }
}
