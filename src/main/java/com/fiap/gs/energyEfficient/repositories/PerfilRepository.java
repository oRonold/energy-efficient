package com.fiap.gs.energyEfficient.repositories;

import com.fiap.gs.energyEfficient.model.perfil.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    @Transactional(readOnly = true)
    Optional<Perfil> findByNome(String nome);

}
