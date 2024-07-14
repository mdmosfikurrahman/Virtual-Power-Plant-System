package com.powerplant.aggregator.service;

import com.powerplant.aggregator.dto.BatteryDTO;
import com.powerplant.aggregator.dto.BatteryRangeStatisticsDTO;
import com.powerplant.aggregator.entity.Battery;

import java.util.List;

/**
 * Service interface for managing Battery entities.
 */
public interface BatteryService {
    List<Battery> saveBatteries(List<BatteryDTO> batteryDTOs);
    BatteryRangeStatisticsDTO getBatteriesByPostcodeRange(String postcodeRange);
    boolean validateBatteryList(List<BatteryDTO> batteryList);
    boolean isValidPostcodeRange(String postcodeRange);
}
