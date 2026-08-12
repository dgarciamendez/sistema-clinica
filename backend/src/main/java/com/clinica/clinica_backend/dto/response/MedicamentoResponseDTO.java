package com.clinica.clinica_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicamentoResponseDTO {

    private Long idMedicamento;
    private String nombre;
    private String descripcion;
    private Integer stock;
    private BigDecimal precioUnitario;
    private LocalDate fechaCaducidad;
    private LocalDateTime fechaRegistro;
    private Boolean activo;
}