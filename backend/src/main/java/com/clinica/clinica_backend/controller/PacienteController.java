package com.clinica.clinica_backend.controller;

import com.clinica.clinica_backend.dto.request.PacienteRequestDTO;
import com.clinica.clinica_backend.dto.response.PacienteResponseDTO;
import com.clinica.clinica_backend.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    // Inyección de dependencias por constructor
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    // GET /api/pacientes -> Obtener todos
    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> findAll() {
        List<PacienteResponseDTO> pacientes = pacienteService.findAll();
        return ResponseEntity.ok(pacientes); // Retorna HTTP 200 OK con la lista
    }

    // GET /api/pacientes/{id} -> Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> findById(@PathVariable Long id) {
        PacienteResponseDTO paciente = pacienteService.findById(id);
        return ResponseEntity.ok(paciente); // Retorna HTTP 200 OK
    }

    // POST /api/pacientes -> Crear un nuevo paciente
    @PostMapping
    public ResponseEntity<PacienteResponseDTO> save(@RequestBody PacienteRequestDTO dto) {
        PacienteResponseDTO nuevoPaciente = pacienteService.save(dto);
        // Retorna HTTP 201 Created indicando que el recurso se creó con éxito
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPaciente);
    }

    // PUT /api/pacientes/{id} -> Actualizar un paciente existente
    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> update(@PathVariable Long id, @RequestBody PacienteRequestDTO dto) {
        PacienteResponseDTO pacienteActualizado = pacienteService.update(id, dto);
        return ResponseEntity.ok(pacienteActualizado); // Retorna HTTP 200 OK
    }

    // DELETE /api/pacientes/{id} -> Borrado lógico de un paciente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        pacienteService.deleteById(id);
        return ResponseEntity.noContent().build(); // Retorna HTTP 204 No Content (eliminado con éxito)
    }
}