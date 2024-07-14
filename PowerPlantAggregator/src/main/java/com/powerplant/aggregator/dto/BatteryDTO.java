package com.powerplant.aggregator.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for Battery entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatteryDTO {
    @NotBlank
    private String name;

    @Pattern(regexp = "\\d{5}", message = "Postcode must be 5 digits")
    private String postCode;

    @Positive
    private int wattCapacity;
}
