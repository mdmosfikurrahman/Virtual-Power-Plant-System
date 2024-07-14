package com.powerplant.aggregator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO for storing battery range statistics.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatteryRangeStatisticsDTO {
    private List<String> batteryNames;
    private int totalWattCapacity;
    private double averageWattCapacity;
}
