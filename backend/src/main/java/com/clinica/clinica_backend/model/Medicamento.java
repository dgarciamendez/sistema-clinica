package com.clinica.clinica_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medicamentos")
public class Medicamento {

    @Id
    @Column("id_medicamento")
    private Long idMedicamento;

    private String nombre;
    private String descripcion;
    private Integer stock;
    private BigDecimal precioUnitario;

    @Column("fecha_caducidad")
    private LocalDate fechaCaducidad;

    @Column("fecha_registro")
    private LocalDateTime fechaRegistro;

    private Boolean activo = true;
}