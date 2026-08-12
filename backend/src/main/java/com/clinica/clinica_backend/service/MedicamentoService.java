package com.clinica.clinica_backend.service;

import com.clinica.clinica_backend.dto.request.MedicamentoRequestDTO;
import com.clinica.clinica_backend.dto.response.MedicamentoResponseDTO;

import java.util.List;

public interface MedicamentoService {

    List<MedicamentoResponseDTO> findAll();

    MedicamentoResponseDTO findById(Long id);

    MedicamentoResponseDTO save(MedicamentoRequestDTO dto);

    MedicamentoResponseDTO update(Long id, MedicamentoRequestDTO dto);

    void deleteById(Long id);
}