package com.clinica.clinica_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaResponseDTO {

    private Long idVenta;
    private LocalDateTime fecha;
    private Long idPaciente;
    private Long idMedico;
    private BigDecimal total;
    private String estado;
    private String observaciones;
}