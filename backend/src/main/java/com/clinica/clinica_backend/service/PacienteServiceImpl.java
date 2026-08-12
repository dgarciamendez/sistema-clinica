package com.clinica.clinica_backend.service;

import com.clinica.clinica_backend.dto.request.PacienteRequestDTO;
import com.clinica.clinica_backend.dto.response.PacienteResponseDTO;
import com.clinica.clinica_backend.exception.ResourceNotFoundException;
import com.clinica.clinica_backend.mapper.PacienteMapper;
import com.clinica.clinica_backend.model.Paciente;
import com.clinica.clinica_backend.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;

    // Inyección por constructor (la mejor práctica)
    public PacienteServiceImpl(PacienteRepository pacienteRepository, PacienteMapper pacienteMapper) {
        this.pacienteRepository = pacienteRepository;
        this.pacienteMapper = pacienteMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PacienteResponseDTO> findAll() {
        return pacienteRepository.findAll().stream()
                .map(pacienteMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PacienteResponseDTO findById(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con ID: " + id));
        return pacienteMapper.toResponseDTO(paciente);
    }

    @Override
    @Transactional
    public PacienteResponseDTO save(PacienteRequestDTO dto) {
        Paciente paciente = pacienteMapper.toEntity(dto);
        Paciente saved = pacienteRepository.save(paciente);
        return pacienteMapper.toResponseDTO(saved);
    }

    @Override
    @Transactional
    public PacienteResponseDTO update(Long id, PacienteRequestDTO dto) {
        Paciente existing = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se puede actualizar. Paciente no encontrado con ID: " + id));

        // Actualizamos campos manualmente (el mapper no actualiza entidades existentes)
        existing.setNombre(dto.getNombre());
        existing.setApellido(dto.getApellido());
        existing.setEmail(dto.getEmail());
        existing.setFechaNacimiento(dto.getFechaNacimiento());
        existing.setGenero(dto.getGenero());
        existing.setDireccion(dto.getDireccion());
        existing.setTelefono(dto.getTelefono());

        pacienteRepository.update(existing);
        return pacienteMapper.toResponseDTO(existing);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se puede eliminar. Paciente no encontrado con ID: " + id));
        pacienteRepository.deleteById(id);
    }
}