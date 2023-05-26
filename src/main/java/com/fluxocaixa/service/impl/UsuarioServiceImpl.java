package com.fluxocaixa.service.impl;

import com.fluxocaixa.entities.Usuario;
import com.fluxocaixa.repository.UsuarioRepository;
import com.fluxocaixa.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<Usuario> findById(String id) {
        return this.usuarioRepository.findById(id);
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        return this.usuarioRepository.findByUsername(username);
    }
}
