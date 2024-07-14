package com.powerplant.aggregator.service;

import com.powerplant.aggregator.dto.BatteryDTO;
import com.powerplant.aggregator.dto.BatteryRangeStatisticsDTO;
import com.powerplant.aggregator.entity.Battery;
import com.powerplant.aggregator.mapper.BatteryMapper;
import com.powerplant.aggregator.repository.BatteryRepository;
import com.powerplant.aggregator.validator.BatteryDTOValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.DataBinder;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BatteryServiceImpl implements BatteryService {

    private final BatteryRepository batteryRepository;
    private final BatteryMapper batteryMapper;
    private final BatteryDTOValidator batteryDTOValidator;

    @Override
    @Transactional
    public List<Battery> saveBatteries(List<BatteryDTO> batteryDTOs) {
        List<Battery> batteries = batteryDTOs.stream()
                .map(batteryMapper::toEntity)
                .collect(Collectors.toList());
        return batteryRepository.saveAll(batteries);
    }

    @Override
    public BatteryRangeStatisticsDTO getBatteriesByPostcodeRange(String postcodeRange) {
        String[] parts = postcodeRange.split("-");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid postcode range format. Expected format: 'startPostcode-endPostcode'.");
        }

        String startPostcode = parts[0];
        String endPostcode = parts[1];

        List<Battery> batteries = batteryRepository.findByPostCodeBetween(startPostcode, endPostcode);

        int totalWattCapacity = batteries.stream().mapToInt(Battery::getWattCapacity).sum();
        double averageWattCapacity = batteries.stream().mapToInt(Battery::getWattCapacity).average().orElse(0.0);

        List<String> batteryNames = batteries.stream()
                .map(Battery::getName)
                .sorted()
                .collect(Collectors.toList());

        return new BatteryRangeStatisticsDTO(batteryNames, totalWattCapacity, averageWattCapacity);
    }

    @Override
    public boolean validateBatteryList(List<BatteryDTO> batteryList) {
        for (BatteryDTO batteryDTO : batteryList) {
            DataBinder binder = new DataBinder(batteryDTO);
            binder.addValidators(batteryDTOValidator);
            binder.validate();
            if (binder.getBindingResult().hasErrors()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isValidPostcodeRange(String postcodeRange) {
        return postcodeRange.matches("\\d{5}-\\d{5}");
    }
}
