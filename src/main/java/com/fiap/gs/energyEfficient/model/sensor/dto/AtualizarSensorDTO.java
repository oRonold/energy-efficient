package com.fiap.gs.energyEfficient.model.sensor.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record AtualizarSensorDTO(
        @Schema(description = "Nome do sensor atualizado")
        @NotEmpty
        String nome) {
}
