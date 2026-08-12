package com.clinica.clinica_backend.controller;

import com.clinica.clinica_backend.dto.request.MedicamentoRequestDTO;
import com.clinica.clinica_backend.dto.response.MedicamentoResponseDTO;
import com.clinica.clinica_backend.service.MedicamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    @GetMapping
    public ResponseEntity<List<MedicamentoResponseDTO>> findAll() {
        return ResponseEntity.ok(medicamentoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(medicamentoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MedicamentoResponseDTO> save(@RequestBody MedicamentoRequestDTO dto) {
        MedicamentoResponseDTO nuevo = medicamentoService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicamentoResponseDTO> update(@PathVariable Long id,
            @RequestBody MedicamentoRequestDTO dto) {
        return ResponseEntity.ok(medicamentoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        medicamentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}