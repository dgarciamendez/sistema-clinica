package com.clinica.clinica_backend.repository;

import com.clinica.clinica_backend.model.Venta;
import com.clinica.clinica_backend.dto.request.ItemVentaDTO;

import java.util.List;
import java.util.Optional;

public interface VentaRepository {

    Long procesarVenta(Long idPaciente, Long idMedico, List<ItemVentaDTO> items, String observaciones);

    Optional<Venta> findById(Long id);

    List<Venta> findAll();

    void anularVenta(Long idVenta);
}