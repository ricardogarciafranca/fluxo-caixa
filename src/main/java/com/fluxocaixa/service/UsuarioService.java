package com.fluxocaixa.service;


import com.fluxocaixa.entities.Usuario;

import java.util.Optional;

public interface UsuarioService {

    Optional<Usuario> findById(String id);

    Optional<Usuario> findByUsername(String username);


}
