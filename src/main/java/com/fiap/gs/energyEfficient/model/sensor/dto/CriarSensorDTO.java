package com.fiap.gs.energyEfficient.model.sensor.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record CriarSensorDTO(
        @Schema(description = "Nome do sensor", example = "Amperímetro")
        @NotEmpty
        String nome) {
}
