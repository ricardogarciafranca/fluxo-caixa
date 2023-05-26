package com.fluxocaixa.repository;

import com.fluxocaixa.entities.Lancamento;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LancamentoRepository extends MongoRepository<Lancamento, String> {

    List<Lancamento> findAllByDateTimeBetweenOrderByDateTimeAsc(LocalDateTime begin, LocalDateTime end);

    Optional<Lancamento> findFirstByIdUsuarioOrderByDateTimeDesc(String idUsuario);

}
