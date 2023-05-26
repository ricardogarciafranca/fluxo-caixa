package com.fluxocaixa.repository;

import com.fluxocaixa.entities.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
     Optional<Usuario> findByUsername(String username);
}
