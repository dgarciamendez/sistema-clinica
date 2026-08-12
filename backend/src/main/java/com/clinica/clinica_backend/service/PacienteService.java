package com.clinica.clinica_backend.service;

import com.clinica.clinica_backend.dto.request.PacienteRequestDTO;
import com.clinica.clinica_backend.dto.response.PacienteResponseDTO;
import java.util.List;

public interface PacienteService {
    List<PacienteResponseDTO> findAll();

    PacienteResponseDTO findById(Long id);

    PacienteResponseDTO save(PacienteRequestDTO dto);

    PacienteResponseDTO update(Long id, PacienteRequestDTO dto);

    void deleteById(Long id);
}