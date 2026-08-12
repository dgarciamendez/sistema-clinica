package com.clinica.clinica_backend.service;

import com.clinica.clinica_backend.dto.request.MedicamentoRequestDTO;
import com.clinica.clinica_backend.dto.response.MedicamentoResponseDTO;
import com.clinica.clinica_backend.exception.ResourceNotFoundException;
import com.clinica.clinica_backend.mapper.MedicamentoMapper;
import com.clinica.clinica_backend.model.Medicamento;
import com.clinica.clinica_backend.repository.MedicamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;
    private final MedicamentoMapper medicamentoMapper;

    public MedicamentoServiceImpl(MedicamentoRepository medicamentoRepository, MedicamentoMapper medicamentoMapper) {
        this.medicamentoRepository = medicamentoRepository;
        this.medicamentoMapper = medicamentoMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicamentoResponseDTO> findAll() {
        return medicamentoRepository.findAll().stream()
                .map(medicamentoMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MedicamentoResponseDTO findById(Long id) {
        Medicamento medicamento = medicamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicamento no encontrado con ID: " + id));
        return medicamentoMapper.toResponseDTO(medicamento);
    }

    @Override
    @Transactional
    public MedicamentoResponseDTO save(MedicamentoRequestDTO dto) {
        Medicamento medicamento = medicamentoMapper.toEntity(dto);
        Medicamento saved = medicamentoRepository.save(medicamento);
        return medicamentoMapper.toResponseDTO(saved);
    }

    @Override
    @Transactional
    public MedicamentoResponseDTO update(Long id, MedicamentoRequestDTO dto) {
        Medicamento existing = medicamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se puede actualizar. Medicamento no encontrado con ID: " + id));

        existing.setNombre(dto.getNombre());
        existing.setDescripcion(dto.getDescripcion());
        existing.setStock(dto.getStock());
        existing.setPrecioUnitario(dto.getPrecioUnitario());
        existing.setFechaCaducidad(dto.getFechaCaducidad());

        medicamentoRepository.update(existing);
        return medicamentoMapper.toResponseDTO(existing);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        medicamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se puede eliminar. Medicamento no encontrado con ID: " + id));
        medicamentoRepository.deleteById(id);
    }
}