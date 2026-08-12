package com.clinica.clinica_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ventas")
public class Venta {

    @Id
    @Column("id_venta")
    private Long idVenta;

    private LocalDateTime fecha;
    private Long idPaciente;
    private Long idMedico;
    private BigDecimal total;
    private String estado;
    private String observaciones;
}