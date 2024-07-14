package com.powerplant.aggregator.config;

import com.powerplant.aggregator.validator.BatteryDTOValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for defining beans.
 */
@Configuration
public class AppConfig {

    @Bean
    public BatteryDTOValidator batteryDTOValidator() {
        return new BatteryDTOValidator();
    }
}
