package com.fiap.gs.energyEfficient.controller;

import com.fiap.gs.energyEfficient.model.morador.dto.CriarMoradorDTO;
import com.fiap.gs.energyEfficient.model.morador.dto.DetalhesMoradorDTO;
import com.fiap.gs.energyEfficient.model.sensor.dto.*;
import com.fiap.gs.energyEfficient.services.MoradorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/moradores")
public class MoradorController {

    @Autowired
    private MoradorService service;

    @Autowired
    private SensorService sensorService;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesMoradorDTO> cadastrar(@Valid @RequestBody CriarMoradorDTO dto, UriComponentsBuilder builder){
        var morador = service.cadastrar(dto);
        var uri = builder.path("/{id}").buildAndExpand(morador).toUri();
        return ResponseEntity.created(uri).body(new DetalhesMoradorDTO(morador));
    }

    @PostMapping("/sensores")
    @Transactional
    public ResponseEntity<DetalhesSensorDTO> criarSensor(@Valid @RequestBody CriarSensorDTO dto, UriComponentsBuilder builder){
        var sensor = sensorService.cadastrar(dto);
        var uri = builder.path("/{id}").buildAndExpand(sensor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesSensorDTO(sensor));
    }

    @PostMapping("/{id}/medida")
    @Transactional
    public ResponseEntity<DetalhesMedicaoDTO> criarMedicao(@PathVariable Long id, @Valid @RequestBody CriarMedidaDTO dto, UriComponentsBuilder builder){
        var medicao = sensorService.criarMedida(id, dto);
        var uri = builder.path("/{id}").buildAndExpand(medicao).toUri();
        return ResponseEntity.created(uri).body(new DetalhesMedicaoDTO(medicao));
    }
}
