package com.willlake.ringingapi.config;

import com.willlake.ringingapi.performances.PerformanceCsvHandling;
import com.willlake.ringingapi.performances.PerformanceIngest;
import com.willlake.ringingapi.performances.PerformanceRepository;
import com.willlake.ringingapi.performances.PerformancesRequester;
import com.willlake.ringingapi.performances.RingerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PerformanceConfig {
    @Bean
    public PerformancesRequester performancesRequester() {
        return new PerformancesRequester();
    }

    @Bean
    public PerformanceIngest performanceIngest(PerformancesRequester performancesRequester, PerformanceRepository performanceRepository, RingerRepository ringerRepository) {
        return new PerformanceIngest(performancesRequester, performanceRepository, ringerRepository);
    }

    @Bean
    public PerformanceCsvHandling performanceCsvHandling(PerformanceRepository performanceRepository, RingerRepository ringerRepository){
        return new PerformanceCsvHandling(performanceRepository, ringerRepository);
    }
}
