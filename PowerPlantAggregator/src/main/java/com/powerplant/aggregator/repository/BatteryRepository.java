package com.powerplant.aggregator.repository;

import com.powerplant.aggregator.entity.Battery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing Battery entities.
 */
@Repository
public interface BatteryRepository extends JpaRepository<Battery, Long> {

    @Query(value = "SELECT * FROM batteries " +
            "WHERE post_code BETWEEN :startPostcode AND :endPostcode", nativeQuery = true)
    List<Battery> findByPostCodeBetween(String startPostcode, String endPostcode);
}
