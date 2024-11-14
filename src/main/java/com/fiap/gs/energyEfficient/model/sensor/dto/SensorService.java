package com.fiap.gs.energyEfficient.model.sensor.dto;

import com.fiap.gs.energyEfficient.model.morador.Morador;
import com.fiap.gs.energyEfficient.model.sensor.Sensor;
import com.fiap.gs.energyEfficient.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    public Sensor cadastrar(CriarSensorDTO dto){
        var sensor = new Sensor(dto);
        var morador = (Morador) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        sensor.setMorador(morador);
        morador.getSensores().add(sensor);

        return sensorRepository.save(sensor);
    }
}
