package com.fiap.gs.energyEfficient.model.sensor.dto;

import com.fiap.gs.energyEfficient.model.sensor.Medicao;

import java.util.List;
import java.util.stream.Collectors;

public record DetalhesMedicaoDTO(Long id, Double valorCorrente, Double valorTensao, Double valorConsumoEmKW) {

    public DetalhesMedicaoDTO(Medicao medicao) {
        this(medicao.getId(), medicao.getValorCorrente().doubleValue(), medicao.getValorTensao().doubleValue(),
                medicao.getValorConsumo().doubleValue());
    }
}
