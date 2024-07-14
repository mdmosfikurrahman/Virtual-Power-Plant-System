package com.powerplant.aggregator.controller;

import com.powerplant.aggregator.dto.BatteryDTO;
import com.powerplant.aggregator.dto.BatteryRangeStatisticsDTO;
import com.powerplant.aggregator.entity.Battery;
import com.powerplant.aggregator.service.BatteryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling Battery-related HTTP requests.
 */
@RestController
@RequestMapping("/batteries")
@RequiredArgsConstructor
public class BatteryController {

    private final BatteryService batteryService;

    @PostMapping
    public ResponseEntity<List<Battery>> createBatteries(@RequestBody List<BatteryDTO> batteryList) {
        if (!batteryService.validateBatteryList(batteryList)) {
            return ResponseEntity.badRequest().body(null);
        }

        List<Battery> savedBatteries = batteryService.saveBatteries(batteryList);
        return ResponseEntity.ok(savedBatteries);
    }

    @GetMapping
    public ResponseEntity<BatteryRangeStatisticsDTO> getBatteriesByPostcodeRange(@RequestParam("postcodeRange") String postcodeRange) {
        if (!batteryService.isValidPostcodeRange(postcodeRange)) {
            return ResponseEntity.badRequest().build();
        }

        BatteryRangeStatisticsDTO statistics = batteryService.getBatteriesByPostcodeRange(postcodeRange);
        return ResponseEntity.ok(statistics);
    }
}
