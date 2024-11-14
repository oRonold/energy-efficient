package com.fiap.gs.energyEfficient.model.sensor.dto;

import com.fiap.gs.energyEfficient.model.sensor.Sensor;

public record DetalhesSensorDTO(Long id, String nome, String proprietario) {

    public DetalhesSensorDTO(Sensor sensor){
        this(sensor.getId(), sensor.getNome(), sensor.getMorador().getNome());
    }
}
