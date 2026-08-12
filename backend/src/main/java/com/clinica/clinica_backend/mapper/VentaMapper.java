package com.clinica.clinica_backend.mapper;

import com.clinica.clinica_backend.dto.response.VentaResponseDTO;
import com.clinica.clinica_backend.model.Venta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VentaMapper {

    VentaResponseDTO toResponseDTO(Venta venta);
}