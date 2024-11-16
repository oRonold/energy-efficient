package com.fiap.gs.energyEfficient.controller;

import com.fiap.gs.energyEfficient.infrastructure.exception.HandlerException;
import com.fiap.gs.energyEfficient.model.sensor.dto.*;
import com.fiap.gs.energyEfficient.services.SensorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/sensores")
@Tag(name = "Sensores", description = "Operações relacionadas com o sensor")
public class SensorController {

    @Autowired
    private SensorService service;

    @Autowired
    private SensorService sensorService;

    @PostMapping("/{id}/medida")
    @Transactional
    @Operation(summary = "Criação de medida de sensor", description = "Retorna os valores de medições com o resultado do consumo")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Medida criada com sucesso", content = @Content(schema = @Schema(implementation = DetalhesMedicaoDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Token JWT inválido ou incorreto", content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados inseridos incorretos", content = @Content)
    })
    public ResponseEntity<DetalhesMedicaoDTO> criarMedicao(@PathVariable Long id, @Valid @RequestBody CriarMedidaDTO dto, UriComponentsBuilder builder){
        var medicao = sensorService.criarMedida(id, dto);
        var uri = builder.path("/{id}").buildAndExpand(medicao).toUri();
        return ResponseEntity.created(uri).body(new DetalhesMedicaoDTO(medicao));
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Cadastro de sensor", description = "Retorna o dados do sensor junto com o morador respectivo")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Sensor criado com sucesso", content = @Content(schema = @Schema(implementation = DetalhesSensorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Token JWT inválido ou incorreto", content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados inseridos incorretos", content = @Content)
    })
    public ResponseEntity<DetalhesSensorDTO> criarSensor(@Valid @RequestBody CriarSensorDTO dto, UriComponentsBuilder builder){
        var sensor = service.cadastrar(dto);
        var uri = builder.path("/{id}").buildAndExpand(sensor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesSensorDTO(sensor));
    }

    @GetMapping
    @Operation(summary = "Listar sensores", description = "Retorna todos os sensores cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Todos os sensores retornados", content = @Content(schema = @Schema(implementation = DetalhesSensorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Token JWT inválido ou incorreto", content = @Content)
    })
    public ResponseEntity<Page<DetalhesSensorDTO>> listarSensores(@PageableDefault(page = 5, sort = "nome") Pageable pageable){
        var page = service.listarSensores(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}/medidas")
    @Operation(summary = "Lista de medidas do sensor", description = "Retorna todas as medidas do sensor informado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Todas as medidas do sensor retornadas", content = @Content(schema = @Schema(implementation = ListarMedidasDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Token JWT inválido ou incorreto", content = @Content),
            @ApiResponse(responseCode = "404", description = "Sensor não encontrado", content = @Content)
    })
    public ResponseEntity<Page<ListarMedidasDTO>> listarTodasMedidas(@PathVariable Long id, @PageableDefault(page = 5, sort = "id") Pageable pageable){
        var medicao = service.listarMedicoes(id, pageable);
        return ResponseEntity.ok(medicao);
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Atualizar sensor", description = "Atualiza o sensor informado")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Sensor atualizado com sucesso", content = @Content(schema = @Schema(implementation = DetalhesSensorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Token JWT inválido ou incorret", content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados inseridos incorretos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Sensor não encontrado", content = @Content)
    })
    public ResponseEntity<DetalhesSensorDTO> atualizarSensor(@PathVariable Long id, @Valid @RequestBody AtualizarSensorDTO dto){
        var sensor = service.atualizar(id, dto);
        return ResponseEntity.ok().body(new DetalhesSensorDTO(sensor));
    }

}
