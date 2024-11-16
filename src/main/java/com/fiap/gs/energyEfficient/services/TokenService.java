package com.fiap.gs.energyEfficient.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fiap.gs.energyEfficient.model.morador.Morador;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class TokenService {

    @Value("${api.token.secret}")
    private String senhaToken;

    public String criarToken(Morador morador){
        try {
            Algorithm algoritmo = Algorithm.HMAC256(senhaToken);
            return JWT.create()
                    .withIssuer("ENERGY EFFICIENT")
                    .withSubject(morador.getUsername())
                    .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                    .sign(algoritmo);
        } catch (JWTCreationException e){
            throw new RuntimeException("Erro ao gerar token JWT");
        }
    }

    public String getSubject(String token) {
        try {
            var algoritmo = Algorithm.HMAC256(senhaToken);
            return JWT.require(algoritmo)
                    .withIssuer("ENERGY EFFICIENT")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT inválido ou expirado");
        }
    }
}
