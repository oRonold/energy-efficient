package com.fiap.gs.energyEfficient.model.sensor.dto;

import com.fiap.gs.energyEfficient.model.sensor.Sensor;
import io.swagger.v3.oas.annotations.media.Schema;

public record DetalhesSensorDTO(
        @Schema(description = "ID do sensor")
        Long id,

        @Schema(description = "Nome do sensor")
        String nome,

        @Schema(description = "Morador selecionado no momento do registro")
        String proprietario) {

    public DetalhesSensorDTO(Sensor sensor){
        this(sensor.getId(), sensor.getNome(), sensor.getMorador().getNome());
    }
}
