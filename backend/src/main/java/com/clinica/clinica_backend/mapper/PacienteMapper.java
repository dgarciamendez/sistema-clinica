package com.clinica.clinica_backend.mapper;

import com.clinica.clinica_backend.dto.request.PacienteRequestDTO;
import com.clinica.clinica_backend.dto.response.PacienteResponseDTO;
import com.clinica.clinica_backend.model.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PacienteMapper {

    // Convierte Entidad (BD) a DTO de Respuesta
    PacienteResponseDTO toResponseDTO(Paciente paciente);

    // Convierte DTO de Request a Entidad (BD)
    // Ignoramos campos que la base de datos maneja sola (id, registro, etc.)
    @Mapping(target = "idPaciente", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "activo", ignore = true)
    Paciente toEntity(PacienteRequestDTO dto);
}