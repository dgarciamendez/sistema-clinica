package com.clinica.clinica_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "detalle_ventas")
public class DetalleVenta {

    private Long idDetalle;
    private Long idVenta;
    private Long idMedicamento;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
}