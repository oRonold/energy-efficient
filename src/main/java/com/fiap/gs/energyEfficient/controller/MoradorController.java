package com.fiap.gs.energyEfficient.controller;

import com.fiap.gs.energyEfficient.model.morador.dto.CriarMoradorDTO;
import com.fiap.gs.energyEfficient.model.morador.dto.DetalhesMoradorDTO;
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
}
