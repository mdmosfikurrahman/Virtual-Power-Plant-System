package com.powerplant.aggregator.service;

import static org.junit.jupiter.api.Assertions.*;

import com.powerplant.aggregator.dto.BatteryDTO;
import com.powerplant.aggregator.entity.Battery;
import com.powerplant.aggregator.mapper.BatteryMapper;
import com.powerplant.aggregator.repository.BatteryRepository;
import com.powerplant.aggregator.validator.BatteryDTOValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BatteryServiceImplTest {

    @Mock
    private BatteryRepository batteryRepository;

    @Mock
    private BatteryMapper batteryMapper;

    @Mock
    private BatteryDTOValidator batteryDTOValidator;

    @InjectMocks
    private BatteryServiceImpl batteryService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveBatteries() {
        List<BatteryDTO> batteryDTOs = Arrays.asList(
                new BatteryDTO("Battery1", "10001", 5000),
                new BatteryDTO("Battery2", "10002", 6000)
        );

        List<Battery> batteries = batteryDTOs.stream()
                .map(batteryMapper::toEntity)
                .collect(Collectors.toList());

        when(batteryMapper.toEntity(any(BatteryDTO.class)))
                .thenReturn(new Battery(), new Battery());

        when(batteryRepository.saveAll(anyList()))
                .thenReturn(batteries);

        // When
        List<Battery> savedBatteries = batteryService.saveBatteries(batteryDTOs);

        // Then
        Assertions.assertEquals(2, savedBatteries.size());
        verify(batteryRepository, times(1)).saveAll(anyList());
    }

    @Test
    public void testGetBatteriesByPostcodeRange() {
        // Implement this test based on your specific repository and service logic
    }

    @Test
    public void testValidateBatteryList() {
        // Given
        List<BatteryDTO> batteryDTOs = Arrays.asList(
                new BatteryDTO("Battery1", "10001", 5000),
                new BatteryDTO("Battery2", "10002", 6000)
        );

        when(batteryDTOValidator.supports(any(Class.class))).thenReturn(true);

        // Mock the validate method of DataBinder
        doNothing().when(batteryDTOValidator).validate(any(), any());

        // When
        boolean isValid = batteryService.validateBatteryList(batteryDTOs);

        // Then
        Assertions.assertTrue(isValid);
        verify(batteryDTOValidator, times(2)).validate(any(), any());
    }

    @Test
    public void testIsValidPostcodeRange() {
        // When & Then
        Assertions.assertTrue(batteryService.isValidPostcodeRange("10000-20000"));
        Assertions.assertFalse(batteryService.isValidPostcodeRange("1000-2000"));
        Assertions.assertFalse(batteryService.isValidPostcodeRange("10000-2000"));
        Assertions.assertFalse(batteryService.isValidPostcodeRange("10000-200000"));
        Assertions.assertFalse(batteryService.isValidPostcodeRange("1000020000"));
    }
}
