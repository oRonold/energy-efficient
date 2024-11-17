package com.fiap.gs.energyEfficient.model.sensor.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.gs.energyEfficient.model.sensor.Medicao;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.time.format.DecimalStyle;

public record ListarMedidasDTO(
        @Schema(description = "ID da medida")
        Long idMedida,
                               @Schema(description = "ID do sensor relacionado")
                               Long idSensor,

                               @Schema(description = "Nome do sensor")
                               String nomeSensor,

                               @Schema(description = "Data da medição criado no momento do cadastro")
                               @JsonFormat(pattern = "dd/MM/yyyy")
                               LocalDateTime dataMedicao,

                               @Schema(description = "Valor da corrente registrada")
                               Double valorCorrente,

                               @Schema(description = "Valor da tensão registrada")
                               Double valorTensao,

                               @Schema(description = "Valor do consumo calculado no momento do registro")
                               Double valorConsumoEmKW) {

    public ListarMedidasDTO(Medicao medicao){
        this(medicao.getId(), medicao.getSensor().getId(), medicao.getSensor().getNome(), medicao.getDataMedicao(),
                medicao.getValorCorrente().doubleValue(),
                medicao.getValorTensao().doubleValue(), medicao.getValorConsumo().doubleValue());
    }

}
