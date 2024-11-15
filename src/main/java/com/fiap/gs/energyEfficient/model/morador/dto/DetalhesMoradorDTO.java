package com.fiap.gs.energyEfficient.model.morador.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.gs.energyEfficient.model.morador.Morador;

import java.time.LocalDate;

public record DetalhesMoradorDTO(Long id, String nome, Integer idade, String estadoCivil, String email, String telefone,
                                 @JsonFormat(pattern = "dd/MM/yyyy")
                                 LocalDate dataNascimento) {

    public DetalhesMoradorDTO(Morador morador) {
        this(morador.getId(), morador.getNome(), morador.getIdade(), morador.getEstadoCivil(), morador.getContatoMorador().getEmail(),
                morador.getContatoMorador().getTelefone(), morador.getDadosMorador().getDataNascimento());
    }
}
