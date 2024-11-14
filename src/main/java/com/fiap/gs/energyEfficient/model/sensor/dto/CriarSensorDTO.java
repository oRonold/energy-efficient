package com.fiap.gs.energyEfficient.model.sensor.dto;

import jakarta.validation.constraints.NotEmpty;

public record CriarSensorDTO(
        @NotEmpty
        String nome) {
}
