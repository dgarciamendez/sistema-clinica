package com.clinica.clinica_backend.mapper;

import com.clinica.clinica_backend.dto.request.MedicoRequestDTO;
import com.clinica.clinica_backend.dto.response.MedicoResponseDTO;
import com.clinica.clinica_backend.model.Medico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MedicoMapper {

    MedicoResponseDTO toResponseDTO(Medico medico);

    @Mapping(target = "idMedico", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "activo", ignore = true)
    Medico toEntity(MedicoRequestDTO dto);
}