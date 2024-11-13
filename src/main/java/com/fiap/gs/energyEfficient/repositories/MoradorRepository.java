package com.fiap.gs.energyEfficient.repositories;

import com.fiap.gs.energyEfficient.model.morador.Morador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoradorRepository extends JpaRepository<Morador, Long> {
}
