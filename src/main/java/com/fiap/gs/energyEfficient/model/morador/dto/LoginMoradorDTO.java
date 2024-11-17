package com.fiap.gs.energyEfficient.model.morador.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public record LoginMoradorDTO(
        @Schema(description = "CPF do morador", example = "12345678900")
        @NotEmpty
        @Length(max = 15)
        String cpf,

        @Schema(description = "Senha criada durante o cadastro", example = "senha123")
        @NotEmpty
        String senha) {
}
