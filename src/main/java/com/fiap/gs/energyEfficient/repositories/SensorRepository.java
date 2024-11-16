package com.fiap.gs.energyEfficient.repositories;

import com.fiap.gs.energyEfficient.model.sensor.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
}
