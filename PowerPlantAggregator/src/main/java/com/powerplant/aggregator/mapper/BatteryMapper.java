package com.powerplant.aggregator.mapper;

import com.powerplant.aggregator.dto.BatteryDTO;
import com.powerplant.aggregator.entity.Battery;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting BatteryDTO to Battery entity.
 */
@Component
public class BatteryMapper {

    public Battery toEntity(BatteryDTO dto) {
        Battery battery = new Battery();
        battery.setName(dto.getName());
        battery.setPostCode(dto.getPostCode());
        battery.setWattCapacity(dto.getWattCapacity());
        return battery;
    }
}
