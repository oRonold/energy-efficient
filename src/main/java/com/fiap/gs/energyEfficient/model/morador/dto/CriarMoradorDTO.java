package com.fiap.gs.energyEfficient.model.morador.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

public record CriarMoradorDTO(
        @NotEmpty
        String nome,
        @NotNull
        @Min(value = 18)
        Integer idade,
        @NotEmpty
        String estadoCivil,
        @NotEmpty
        @Email
        String email,
        @NotEmpty
        String senha,
        @NotEmpty
        @Length(max = 15)
        String telefone,
        @NotEmpty
        @Length(max = 15)
        String cpf,
        @NotNull
        @Past
        LocalDate dataNascimento,
        @NotEmpty
        @Length(max = 15)
        String rg,
        @NotEmpty
        String role) {
}
