package com.fiap.gs.energyEfficient.controller;

import com.fiap.gs.energyEfficient.model.sensor.dto.CriarMedidaDTO;
import com.fiap.gs.energyEfficient.model.sensor.dto.CriarSensorDTO;
import com.fiap.gs.energyEfficient.model.sensor.dto.DetalhesSensorDTO;
import com.fiap.gs.energyEfficient.model.sensor.dto.SensorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private SensorService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesSensorDTO> criarSensor(@Valid @RequestBody CriarSensorDTO dto, UriComponentsBuilder builder){
        var sensor = service.cadastrar(dto);
        var uri = builder.path("/{id}").buildAndExpand(sensor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesSensorDTO(sensor));
    }
}
