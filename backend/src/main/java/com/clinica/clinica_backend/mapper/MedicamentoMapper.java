package com.clinica.clinica_backend.mapper;

import com.clinica.clinica_backend.dto.request.MedicamentoRequestDTO;
import com.clinica.clinica_backend.dto.response.MedicamentoResponseDTO;
import com.clinica.clinica_backend.model.Medicamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MedicamentoMapper {

    MedicamentoResponseDTO toResponseDTO(Medicamento medicamento);

    @Mapping(target = "idMedicamento", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "activo", ignore = true)
    Medicamento toEntity(MedicamentoRequestDTO dto);
}