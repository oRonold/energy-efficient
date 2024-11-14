package com.fiap.gs.energyEfficient.services;

import com.fiap.gs.energyEfficient.model.morador.Morador;
import com.fiap.gs.energyEfficient.model.morador.dto.CriarMoradorDTO;
import com.fiap.gs.energyEfficient.model.morador.dto.DetalhesMoradorDTO;
import com.fiap.gs.energyEfficient.model.morador.dto.LoginMoradorDTO;
import com.fiap.gs.energyEfficient.model.perfil.Perfil;
import com.fiap.gs.energyEfficient.repositories.MoradorRepository;
import com.fiap.gs.energyEfficient.repositories.PerfilRepository;
import jakarta.validation.ValidationException;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private PerfilRepository perfilRespotiry;

    @Autowired
    private PasswordEncoder encoder;

    public Morador cadastrar(CriarMoradorDTO dto){
        var morador = new Morador(dto);
        morador.getContatoMorador().setMorador(morador);
        morador.getDadosMorador().setMorador(morador);
        morador.getDadosMorador().setSenha(encoder.encode(dto.senha()));

        String role = dto.role().toUpperCase();

        var perfil = perfilRespotiry.findByNome(role).orElseThrow(() -> new IllegalArgumentException("Perfil não encontrado: " + role));
        morador.getPerfis().add(perfil);

        perfilRespotiry.save(perfil);
        return repository.save(morador);
    }

    public String login(LoginMoradorDTO dto){
        Morador morador = repository.findByDadosMoradorCpf(dto.cpf()).orElseThrow(() -> new ValidationException("CPF ou senha incorretos"));
        if(!encoder.matches(dto.senha(), morador.getDadosMorador().getSenha())){
            throw new ValidationException("Senha incorreta");
        }

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(dto.cpf(), dto.senha()));

        return tokenService.criarToken(morador);
    }

    public Morador buscaPorId(Long id){
        return repository.getReferenceById(id);
    }
}
