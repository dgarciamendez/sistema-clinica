package com.clinica.clinica_backend.service;

import com.clinica.clinica_backend.dto.request.VentaRequestDTO;
import com.clinica.clinica_backend.dto.response.VentaResponseDTO;

import java.util.List;

public interface VentaService {

    VentaResponseDTO procesarVenta(VentaRequestDTO request);

    VentaResponseDTO findById(Long id);

    List<VentaResponseDTO> findAll();

    void anularVenta(Long id);
}