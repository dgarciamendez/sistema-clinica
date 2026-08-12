package com.clinica.clinica_backend.repository;

import com.clinica.clinica_backend.model.Medicamento;

import java.util.List;
import java.util.Optional;

public interface MedicamentoRepository {

    List<Medicamento> findAll();

    Optional<Medicamento> findById(Long id);

    Medicamento save(Medicamento medicamento);

    void update(Medicamento medicamento);

    void deleteById(Long id);
}