package com.clinica.clinica_backend.service;

import com.clinica.clinica_backend.dto.request.VentaRequestDTO;
import com.clinica.clinica_backend.dto.response.VentaResponseDTO;
import com.clinica.clinica_backend.exception.BusinessException;
import com.clinica.clinica_backend.exception.ResourceNotFoundException;
import com.clinica.clinica_backend.mapper.VentaMapper;
import com.clinica.clinica_backend.model.Venta;
import com.clinica.clinica_backend.repository.VentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;
    private final VentaMapper ventaMapper;

    public VentaServiceImpl(VentaRepository ventaRepository, VentaMapper ventaMapper) {
        this.ventaRepository = ventaRepository;
        this.ventaMapper = ventaMapper;
    }

    @Override
    @Transactional
    public VentaResponseDTO procesarVenta(VentaRequestDTO request) {
        try {
            Long idVenta = ventaRepository.procesarVenta(
                    request.getIdPaciente(),
                    request.getIdMedico(),
                    request.getItems(),
                    request.getObservaciones());
            Venta venta = ventaRepository.findById(idVenta)
                    .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada después de procesar"));
            return ventaMapper.toResponseDTO(venta);
        } catch (Exception e) {
            throw new BusinessException("Error al procesar la venta: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public VentaResponseDTO findById(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada con ID: " + id));
        return ventaMapper.toResponseDTO(venta);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VentaResponseDTO> findAll() {
        return ventaRepository.findAll().stream()
                .map(ventaMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional
    public void anularVenta(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("No se puede anular. Venta no encontrada con ID: " + id));
        if ("ANULADA".equals(venta.getEstado())) {
            throw new BusinessException("La venta ya está anulada");
        }
        ventaRepository.anularVenta(id);
    }
}