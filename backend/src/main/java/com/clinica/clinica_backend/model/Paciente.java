package com.clinica.clinica_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pacientes")
public class Paciente {
    @Id
    @Column("id_paciente")
    private Long idPaciente;
    private String nombre;
    private String apellido;

    private java.time.LocalDate fechaNacimiento;
    private String genero;
    private String direccion;
    private String telefono;
    private String email;
    @Column("fecha_registro")
    private java.time.LocalDateTime fechaRegistro;
    private Boolean activo = true;

    // Getters and Setters
}