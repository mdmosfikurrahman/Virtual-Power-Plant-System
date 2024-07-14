package com.powerplant.aggregator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a Battery.
 */
@Data
@Entity
@Table(name = "batteries")
@AllArgsConstructor
@NoArgsConstructor
public class Battery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String postCode;
    private int wattCapacity;
}
