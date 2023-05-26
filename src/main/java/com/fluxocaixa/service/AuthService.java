package com.fluxocaixa.service;

import com.fluxocaixa.entities.Usuario;
import com.fluxocaixa.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private JwtUtil jwtUtil;

    public String generateToken(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        return jwtUtil.createToken(usuario);
    }

    public boolean isTokenValid(String token) {
        try {
            jwtUtil.validar(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getTokenId(String token) {
        Claims body = jwtUtil.validar(token);
        return String.valueOf(body.getSubject());
    }

}
