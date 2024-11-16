package com.fiap.gs.energyEfficient.model.sensor.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.gs.energyEfficient.model.sensor.Medicao;

import java.time.LocalDateTime;

public record ListarMedidasDTO(Long idMedida,
                               Long idSensor,
                               String nomeSensor,
                               @JsonFormat(pattern = "dd/MM/yyyy")
                               LocalDateTime dataMedicao,
                               Double valorCorrente,
                               Double valorTensao,
                               Double valorConsumoEmKW) {

    public ListarMedidasDTO(Medicao medicao){
        this(medicao.getId(), medicao.getSensor().getId(), medicao.getSensor().getNome(), medicao.getDataMedicao(),
                medicao.getValorCorrente().doubleValue(),
                medicao.getValorTensao().doubleValue(), medicao.getValorConsumo().doubleValue());
    }

}
