package br.com.nexus.televideo.service;

import br.com.nexus.televideo.domain.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private final String secretPass = "secreta";

    public String gerarToken(User user) {
        return JWT.create()
                .withIssuer("Produtos")
                .withSubject(user.getEmail())
                .withClaim("id", user.getID())
                .withExpiresAt(LocalDateTime.now().plusMinutes(10).toInstant(ZoneOffset.of("-03:00")))
                .sign(Algorithm.HMAC256(secretPass));
    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256(secretPass))
                .withIssuer("Produtos")
                .build().verify(token).getSubject();
    }

}
