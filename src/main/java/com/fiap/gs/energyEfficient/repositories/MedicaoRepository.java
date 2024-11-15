package com.fiap.gs.energyEfficient.repositories;

import com.fiap.gs.energyEfficient.model.sensor.Medicao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicaoRepository extends JpaRepository<Medicao, Long> {
}
