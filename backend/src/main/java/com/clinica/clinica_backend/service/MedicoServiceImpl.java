package com.clinica.clinica_backend.service;

import com.clinica.clinica_backend.dto.request.MedicoRequestDTO;
import com.clinica.clinica_backend.dto.response.MedicoResponseDTO;
import com.clinica.clinica_backend.exception.ResourceNotFoundException;
import com.clinica.clinica_backend.mapper.MedicoMapper;
import com.clinica.clinica_backend.model.Medico;
import com.clinica.clinica_backend.repository.MedicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicoServiceImpl implements MedicoService {

    private final MedicoRepository medicoRepository;
    private final MedicoMapper medicoMapper;

    public MedicoServiceImpl(MedicoRepository medicoRepository, MedicoMapper medicoMapper) {
        this.medicoRepository = medicoRepository;
        this.medicoMapper = medicoMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicoResponseDTO> findAll() {
        return medicoRepository.findAll().stream()
                .map(medicoMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MedicoResponseDTO findById(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico no encontrado con ID: " + id));
        return medicoMapper.toResponseDTO(medico);
    }

    @Override
    @Transactional
    public MedicoResponseDTO save(MedicoRequestDTO dto) {
        Medico medico = medicoMapper.toEntity(dto);
        Medico saved = medicoRepository.save(medico);
        return medicoMapper.toResponseDTO(saved);
    }

    @Override
    @Transactional
    public MedicoResponseDTO update(Long id, MedicoRequestDTO dto) {
        Medico existing = medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se puede actualizar. Médico no encontrado con ID: " + id));

        // Actualizamos con los datos del DTO
        existing.setNombre(dto.getNombre());
        existing.setApellido(dto.getApellido());
        existing.setEspecialidad(dto.getEspecialidad());
        existing.setTelefono(dto.getTelefono());
        existing.setEmail(dto.getEmail());

        medicoRepository.update(existing);
        return medicoMapper.toResponseDTO(existing);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se puede eliminar. Médico no encontrado con ID: " + id));
        medicoRepository.deleteById(id);
    }
}