package com.fiap.gs.energyEfficient.model.sensor.dto;

import com.fiap.gs.energyEfficient.model.sensor.Medicao;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.stream.Collectors;

public record DetalhesMedicaoDTO(
        @Schema(description = "ID da medição")
        Long id,

        @Schema(description = "Valor da corrente registrada")
        Double valorCorrente,

        @Schema(description = "Valor da tensão registrada")
        Double valorTensao,

        @Schema(description = "Valor do consumo calculado no momento do registro")
        Double valorConsumoEmKW) {

    public DetalhesMedicaoDTO(Medicao medicao) {
        this(medicao.getId(), medicao.getValorCorrente().doubleValue(), medicao.getValorTensao().doubleValue(),
                medicao.getValorConsumo().doubleValue());
    }
}
