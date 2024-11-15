package com.fiap.gs.energyEfficient.repositories;

import com.fiap.gs.energyEfficient.model.sensor.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
}
