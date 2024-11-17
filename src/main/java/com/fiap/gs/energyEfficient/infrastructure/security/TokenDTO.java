package com.fiap.gs.energyEfficient.infrastructure.security;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record TokenDTO(
        @Schema(description = "Token para a validação de acesso aos endpoints")
        @NotEmpty
        String token) {
}
