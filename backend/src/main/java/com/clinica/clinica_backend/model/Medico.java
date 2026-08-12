package com.clinica.clinica_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medicos")
public class Medico {

    @Id
    @Column("id_medico")
    private Long idMedico;

    private String nombre;
    private String apellido;
    private String especialidad;
    private String telefono;
    private String email;

    @Column("fecha_registro")
    private LocalDateTime fechaRegistro;

    private Boolean activo = true;
}