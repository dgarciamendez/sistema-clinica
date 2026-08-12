package com.clinica.clinica_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoResponseDTO {

    private Long idMedico;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String telefono;
    private String email;
    private LocalDateTime fechaRegistro;
    private Boolean activo;
}