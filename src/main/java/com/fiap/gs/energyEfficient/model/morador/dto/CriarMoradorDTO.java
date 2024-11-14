package com.fiap.gs.energyEfficient.model.morador.dto;

import java.time.LocalDate;

public record CriarMoradorDTO(String nome, Integer idade, String estadoCivil, String email, String senha, String telefone,
                              String cpf, LocalDate dataNascimento, String rg, String role) {
}
