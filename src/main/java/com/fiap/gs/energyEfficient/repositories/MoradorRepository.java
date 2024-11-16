package com.fiap.gs.energyEfficient.repositories;

import com.fiap.gs.energyEfficient.model.morador.Morador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MoradorRepository extends JpaRepository<Morador, Long> {

    @Transactional(readOnly = true)
    Optional<Morador> findByDadosMoradorCpf(String cpf);

    @Transactional(readOnly = true)
    @Query("select m from Morador m where m.dadosMorador.cpf = ?1")
    UserDetails recuperarMoradorPorCpf(String cpf);

}
