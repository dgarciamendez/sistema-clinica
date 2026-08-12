package com.clinica.clinica_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteResponseDTO {

    private Long idPaciente;
    private String nombre;
    private String apellido;
    private String email;
    private LocalDate fechaNacimiento;
    private String genero;
    private String direccion;
    private String telefono;
    private LocalDateTime fechaRegistro;
    private Boolean activo;
}