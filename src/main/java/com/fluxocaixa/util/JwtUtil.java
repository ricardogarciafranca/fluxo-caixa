package com.fluxocaixa.util;

import com.fluxocaixa.entities.Usuario;
import com.fluxocaixa.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration.days}")
    private Integer expiration;

    public static Map<String, Object> headersJWT;

    static {
        headersJWT = new HashMap<>();
        headersJWT.put("typ", "JWT");
        headersJWT.put("alg", HS512);
    }

    public String createToken(Usuario usuario) {

        return Jwts.builder()
                .setHeader(headersJWT)

                //JWT-ID, identificador exclusivo para o token, para evitar duplicidade.
                .setId(UUID.randomUUID().toString())

                //Assunto, identifica um termo especifico para a aplicacao.
                .setSubject(usuario.getId())

                //Emissor, identifica quem emitiu o JWT.
                .setIssuer("jwt")

                //Emitida em, identifica a hora em que o JWT foi publicado.
                .setIssuedAt(new Date())

                //Expiracao, identifica o tempo de expiracao do token.
                .setExpiration(Date.from(LocalDateTime.now().plusDays(expiration)
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))

                //Sign: como o token será assinado;
                .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)), HS512).compact();

        // nbf -> Nao Antes, identifica que o JWT nao pode ser aceito antes do tempo especificado.
    }


    public Claims validar(String token) throws UnauthorizedException {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token);
            return claimsJws.getBody();
        } catch (Exception e) {
            throw new UnauthorizedException("Token expirado ou invalido");
        }

    }

}
