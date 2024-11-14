package com.fiap.gs.energyEfficient.controller;

import com.fiap.gs.energyEfficient.infrastructure.security.TokenDTO;
import com.fiap.gs.energyEfficient.model.morador.dto.LoginMoradorDTO;
import com.fiap.gs.energyEfficient.services.MoradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private MoradorService service;

    @PostMapping
    public ResponseEntity<TokenDTO> login(@RequestBody LoginMoradorDTO dto){
        var token = service.login(dto);
        return ResponseEntity.ok(new TokenDTO(token));
    }
}
