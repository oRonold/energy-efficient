package com.fiap.gs.energyEfficient.controller;

import com.fiap.gs.energyEfficient.infrastructure.security.TokenDTO;
import com.fiap.gs.energyEfficient.model.morador.dto.LoginMoradorDTO;
import com.fiap.gs.energyEfficient.services.MoradorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Tag(name = "Login", description = "Operações relacionadas ao login de usuário")
public class LoginController {

    @Autowired
    private MoradorService service;

    @PostMapping
    @Operation(summary = "Login de usuário", description = "Retorna o token JWT para a aplicação")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso", content = @Content(schema = @Schema(implementation = TokenDTO.class))),
            @ApiResponse(responseCode = "400", description = "CPF ou senha incorretos", content = @Content)
    })
    public ResponseEntity<TokenDTO> login(@Valid @RequestBody LoginMoradorDTO dto){
        var token = service.login(dto);
        return ResponseEntity.ok(new TokenDTO(token));
    }
}
