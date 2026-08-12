package com.clinica.clinica_backend.repository;

import com.clinica.clinica_backend.model.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository {

    List<Paciente> findAll();

    Optional<Paciente> findById(Long id);

    Paciente save(Paciente paciente);

    void update(Paciente paciente);

    void deleteById(Long id);
}