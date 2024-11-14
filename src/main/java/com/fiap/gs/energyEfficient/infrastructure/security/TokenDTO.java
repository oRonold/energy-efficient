package com.fiap.gs.energyEfficient.infrastructure.security;

import jakarta.validation.constraints.NotEmpty;

public record TokenDTO(
        @NotEmpty
        String token) {
}
