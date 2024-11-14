package com.fiap.gs.energyEfficient.model.morador.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public record LoginMoradorDTO(
        @NotEmpty
        @Length(max = 15)
        String cpf,
        @NotEmpty
        String senha) {
}
