package com.fiap.gs.energyEfficient.controller;

import com.fiap.gs.energyEfficient.model.morador.dto.CriarMoradorDTO;
import com.fiap.gs.energyEfficient.model.morador.dto.DetalhesMoradorDTO;
import com.fiap.gs.energyEfficient.model.sensor.dto.CriarMedidaDTO;
import com.fiap.gs.energyEfficient.model.sensor.dto.DetalhesMedicaoDTO;
import com.fiap.gs.energyEfficient.model.sensor.dto.DetalhesSensorDTO;
import com.fiap.gs.energyEfficient.model.sensor.dto.SensorService;
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

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesMoradorDTO> buscarPorId(@PathVariable Long id){
        var morador = service.buscaPorId(id);
        return ResponseEntity.ok(new DetalhesMoradorDTO(morador));
    }

    @PostMapping("/{id}/medida")
    @Transactional
    public ResponseEntity<DetalhesMedicaoDTO> criarMedicao(@PathVariable Long id, @Valid @RequestBody CriarMedidaDTO dto, UriComponentsBuilder builder){
        var medicao = sensorService.criarMedida(id, dto);
        var uri = builder.path("/{id}").buildAndExpand(medicao).toUri();
        return ResponseEntity.created(uri).body(new DetalhesMedicaoDTO(medicao));
    }
}
