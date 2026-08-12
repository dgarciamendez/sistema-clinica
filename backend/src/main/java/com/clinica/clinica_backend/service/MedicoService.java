package com.clinica.clinica_backend.service;

import com.clinica.clinica_backend.dto.request.MedicoRequestDTO;
import com.clinica.clinica_backend.dto.response.MedicoResponseDTO;

import java.util.List;

public interface MedicoService {

    List<MedicoResponseDTO> findAll();

    MedicoResponseDTO findById(Long id);

    MedicoResponseDTO save(MedicoRequestDTO dto);

    MedicoResponseDTO update(Long id, MedicoRequestDTO dto);

    void deleteById(Long id);
}