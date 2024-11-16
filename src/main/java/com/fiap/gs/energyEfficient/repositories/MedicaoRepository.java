package com.fiap.gs.energyEfficient.repositories;

import com.fiap.gs.energyEfficient.model.sensor.Medicao;
import com.fiap.gs.energyEfficient.model.sensor.Sensor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedicaoRepository extends JpaRepository<Medicao, Long> {

    @Query("select m from Medicao m where m.sensor.id = ?1")
    Page<Medicao> procurarTodasMedidasPorSensor(Long id, Pageable pageable);
}
