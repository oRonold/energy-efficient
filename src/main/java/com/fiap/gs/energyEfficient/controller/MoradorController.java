package com.fiap.gs.energyEfficient.controller;

import com.fiap.gs.energyEfficient.infrastructure.exception.HandlerException;
import com.fiap.gs.energyEfficient.model.morador.dto.CriarMoradorDTO;
import com.fiap.gs.energyEfficient.model.morador.dto.DetalhesMoradorDTO;
import com.fiap.gs.energyEfficient.model.sensor.dto.*;
import com.fiap.gs.energyEfficient.services.MoradorService;
import com.fiap.gs.energyEfficient.services.SensorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/moradores")
@Tag(name = "Morador", description = "Operações relaciondas ao morador")
public class MoradorController {

    @Autowired
    private MoradorService service;

    @PostMapping
    @Transactional
    @Operation(summary = "Cadastro de morador", description = "Retorna os dados do morador cadastrado")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Morador cadastrado com sucesso", content = @Content(schema = @Schema(implementation = DetalhesMoradorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inseridos incorretos", content = @Content)
    })
    public ResponseEntity<DetalhesMoradorDTO> cadastrar(@Valid @RequestBody CriarMoradorDTO dto, UriComponentsBuilder builder){
        var morador = service.cadastrar(dto);
        var uri = builder.path("/{id}").buildAndExpand(morador).toUri();
        return ResponseEntity.created(uri).body(new DetalhesMoradorDTO(morador));
    }
}
