package com.fluxocaixa.config;

import com.fluxocaixa.service.AuthService;
import com.fluxocaixa.entities.Usuario;
import com.fluxocaixa.service.UsuarioService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Configuration
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final AuthService authService;

    private final UsuarioService usuarioService;

    public TokenAuthenticationFilter(AuthService authService, UsuarioService usuarioService) {
        this.authService = authService;
        this.usuarioService = usuarioService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String tokenFromHeader = getTokenFromHeader(request);
        boolean tokenValid = authService.isTokenValid(tokenFromHeader);
        if (tokenValid) {
            this.authenticate(tokenFromHeader);
        }

        filterChain.doFilter(request, response);
    }

    private void authenticate(String tokenFromHeader) {
        String id = authService.getTokenId(tokenFromHeader);

        Optional<Usuario> optionalUser = usuarioService.findById(id);

        if (optionalUser.isPresent()) {
            Usuario user = optionalUser.get();
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }

    private static String getTokenFromHeader(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        } else {
            return token.substring(7);
        }
    }

}