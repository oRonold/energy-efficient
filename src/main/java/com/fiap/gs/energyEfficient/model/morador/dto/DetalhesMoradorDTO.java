package com.fiap.gs.energyEfficient.model.morador.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.gs.energyEfficient.model.morador.Morador;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record DetalhesMoradorDTO(
        @Schema(description = "ID do morador")
        Long id,

        @Schema(description = "Nome do morador")
        String nome,

        @Schema(description = "Idade do morador")
        Integer idade,

        @Schema(description = "Estado civil")
        String estadoCivil,

        @Schema(description = "Email do morador")
        String email,

        @Schema(description = "Telefone do morador")
        String telefone,
                                 @Schema(description = "Data de nascimento do morador")
                                 @JsonFormat(pattern = "dd/MM/yyyy")
                                 LocalDate dataNascimento) {

    public DetalhesMoradorDTO(Morador morador) {
        this(morador.getId(), morador.getNome(), morador.getIdade(), morador.getEstadoCivil(), morador.getContatoMorador().getEmail(),
                morador.getContatoMorador().getTelefone(), morador.getDadosMorador().getDataNascimento());
    }
}
