package com.clinica.clinica_backend.repository;

import com.clinica.clinica_backend.model.Medico;

import java.util.List;
import java.util.Optional;

public interface MedicoRepository {

    List<Medico> findAll();

    Optional<Medico> findById(Long id);

    Medico save(Medico medico);

    void update(Medico medico);

    void deleteById(Long id);
}