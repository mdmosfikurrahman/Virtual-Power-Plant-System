package com.powerplant.aggregator.validator;

import com.powerplant.aggregator.dto.BatteryDTO;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator class for validating BatteryDTO.
 */
@Component
public class BatteryDTOValidator implements Validator {

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return BatteryDTO.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        BatteryDTO batteryDTO = (BatteryDTO) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty", "Battery name must not be empty");

        if (batteryDTO.getPostCode() != null && !batteryDTO.getPostCode().matches("\\d{5}")) {
            errors.rejectValue("postCode", "postCode.invalid", "Postcode must be 5 digits");
        }

        if (batteryDTO.getWattCapacity() <= 0) {
            errors.rejectValue("wattCapacity", "wattCapacity.invalid", "Watt capacity must be positive");
        }
    }
}
