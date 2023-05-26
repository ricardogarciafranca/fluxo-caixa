package com.fluxocaixa.controller;

import com.fluxocaixa.reponse.AuthenticationResponse;
import com.fluxocaixa.request.AuthenticationRequest;
import com.fluxocaixa.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService tokenService;

    @PostMapping
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Validated AuthenticationRequest request) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        String token = tokenService.generateToken(authentication);
        return ResponseEntity.ok(new AuthenticationResponse(token, "Bearer"));
    }
}
