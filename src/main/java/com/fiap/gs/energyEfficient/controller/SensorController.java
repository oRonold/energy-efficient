package com.fiap.gs.energyEfficient.controller;

import com.fiap.gs.energyEfficient.model.sensor.dto.CriarSensorDTO;
import com.fiap.gs.energyEfficient.model.sensor.dto.DetalhesSensorDTO;
import com.fiap.gs.energyEfficient.services.SensorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/sensores")
public class SensorController {

    @Autowired
    private SensorService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesSensorDTO> criarSensor(@Valid @RequestBody CriarSensorDTO dto, UriComponentsBuilder builder){
        var sensor = service.cadastrar(dto);
        var uri = builder.path("/{id}").buildAndExpand(sensor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesSensorDTO(sensor));
    }

    @GetMapping
    public ResponseEntity<Page<DetalhesSensorDTO>> listarSensores(Pageable pageable){
        var page = service.listarSensores(pageable);
        return ResponseEntity.ok(page);
    }

}
