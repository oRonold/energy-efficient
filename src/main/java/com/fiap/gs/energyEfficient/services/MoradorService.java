package com.fiap.gs.energyEfficient.services;

import com.fiap.gs.energyEfficient.model.morador.Morador;
import com.fiap.gs.energyEfficient.model.morador.dto.CriarMoradorDTO;
import com.fiap.gs.energyEfficient.model.morador.dto.LoginMoradorDTO;
import com.fiap.gs.energyEfficient.repositories.MoradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MoradorService {

    @Autowired
    private MoradorRepository repository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder encoder;

    public Morador cadastrar(CriarMoradorDTO dto){
        var morador = new Morador(dto);
        morador.getContatoMorador().setMorador(morador);
        morador.getDadosMorador().setMorador(morador);
        morador.getDadosMorador().setSenha(encoder.encode(dto.senha()));

        return repository.save(morador);
    }

    public String login(LoginMoradorDTO dto){
        var token = new UsernamePasswordAuthenticationToken(dto.cpf(), dto.senha());
        var auth = manager.authenticate(token);
        return tokenService.criarToken((Morador) auth.getPrincipal());
    }
}
