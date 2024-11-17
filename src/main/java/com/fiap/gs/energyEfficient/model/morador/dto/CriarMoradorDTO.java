package com.fiap.gs.energyEfficient.model.morador.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

@Schema(description = "DTO para criar um novo morador")
public record CriarMoradorDTO(
        @Schema(description = "Nome do morador", example = "Ronald")
        @NotEmpty
        String nome,

        @Schema(description = "Idade do morador", example = "18")
        @NotNull
        @Min(value = 18)
        Integer idade,

        @Schema(description = "Estado civil do morador", example = "Solteiro")
        @NotEmpty
        String estadoCivil,

        @Schema(description = "Email do morador", example = "gustavo@gmail.com.br")
        @NotEmpty
        @Email
        String email,

        @Schema(description = "Senha do email", example = "senha123")
        @NotEmpty
        String senha,

        @Schema(description = "Numero de telefone do morador", example = "11967383861")
        @NotEmpty
        @Length(max = 15)
        String telefone,

        @Schema(description = "Numero de CPF do morador", example = "12345678900")
        @NotEmpty
        @Length(max = 15)
        String cpf,

        @Schema(description = "Data de nascimento do morador", example = "18092004")
        @NotNull
        @Past
        LocalDate dataNascimento,

        @Schema(description = "Numero do RG do morador", example = "123456789")
        @NotEmpty
        @Length(max = 15)
        String rg) {
}
