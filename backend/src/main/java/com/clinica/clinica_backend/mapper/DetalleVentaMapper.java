package com.clinica.clinica_backend.mapper;

import com.clinica.clinica_backend.dto.request.DetalleVentaRequestDTO;
import com.clinica.clinica_backend.dto.response.DetalleVentaResponseDTO;
import com.clinica.clinica_backend.model.DetalleVenta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DetalleVentaMapper {

    @Mapping(target = "idDetalle", ignore = true)
    @Mapping(target = "idVenta", ignore = true)
    @Mapping(target = "subtotal", ignore = true)
    DetalleVenta toEntity(DetalleVentaRequestDTO dto);

    DetalleVentaResponseDTO toResponseDTO(DetalleVenta detalle);
}