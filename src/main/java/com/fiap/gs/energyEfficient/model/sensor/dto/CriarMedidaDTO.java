package com.fiap.gs.energyEfficient.model.sensor.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

public record CriarMedidaDTO(
        @Schema(description = "ID do sensor para a medição")
        @NotNull
        Long idSensor,

        @Schema(description = "Valor da corrente registrada", example = "12")
        @NotNull
        @Max(value = 50)
        BigDecimal valorCorrente,

        @Schema(description = "Valor da tensão registrada", example = "220")
        @NotNull
        BigDecimal valorTensao,

        @Schema(description = "Valor da temperatura registrada", example = "40")
        @NotNull
        BigDecimal valorTemperatura) {
}
