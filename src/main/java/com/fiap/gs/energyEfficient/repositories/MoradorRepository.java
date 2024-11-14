package com.fiap.gs.energyEfficient.repositories;

import com.fiap.gs.energyEfficient.model.morador.Morador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MoradorRepository extends JpaRepository<Morador, Long> {

    Optional<Morador> findByDadosMoradorCpf(String cpf);

    @Query("select m from Morador m where m.dadosMorador.cpf = ?1")
    Morador recuperarMoradorPorCpf(String cpf);

}
