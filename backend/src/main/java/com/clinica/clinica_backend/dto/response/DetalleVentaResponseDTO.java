package com.clinica.clinica_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVentaResponseDTO {

    private Long idDetalle;
    private Long idMedicamento;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
}