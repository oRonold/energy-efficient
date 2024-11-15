package com.fiap.gs.energyEfficient.model.sensor.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

public record CriarMedidaDTO(
        @NotNull
        Long idSensor,
        @NotNull
        @Max(value = 50)
        BigDecimal valorCorrente,
        @NotNull
        BigDecimal valorTensao,
        @NotNull
        BigDecimal valorTemperatura) {
}
