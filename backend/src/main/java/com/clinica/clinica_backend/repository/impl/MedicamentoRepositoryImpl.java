package com.clinica.clinica_backend.repository.impl;

import com.clinica.clinica_backend.model.Medicamento;
import com.clinica.clinica_backend.repository.MedicamentoRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MedicamentoRepositoryImpl implements MedicamentoRepository {

    private final JdbcTemplate jdbcTemplate;

    public MedicamentoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Medicamento> findAll() {
        String sql = "SELECT * FROM fn_obtener_todos_medicamentos()";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Medicamento.class));
    }

    @Override
    public Optional<Medicamento> findById(Long id) {
        String sql = "SELECT * FROM fn_obtener_medicamento_por_id(?)";
        List<Medicamento> resultados = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Medicamento.class), id);
        return resultados.stream().findFirst();
    }

    @Override
    public Medicamento save(Medicamento medicamento) {
        String sql = "SELECT * FROM fn_insertar_medicamento(?, ?, ?, ?, ?)";
        Long idGenerado = jdbcTemplate.queryForObject(sql, Long.class,
                medicamento.getNombre(),
                medicamento.getDescripcion(),
                medicamento.getStock(),
                medicamento.getPrecioUnitario(),
                medicamento.getFechaCaducidad());
        medicamento.setIdMedicamento(idGenerado);
        return medicamento;
    }

    @Override
    public void update(Medicamento medicamento) {
        String sql = "SELECT fn_actualizar_medicamento(?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                medicamento.getIdMedicamento(),
                medicamento.getNombre(),
                medicamento.getDescripcion(),
                medicamento.getStock(),
                medicamento.getPrecioUnitario(),
                medicamento.getFechaCaducidad());
    }

    @Override
    public void deleteById(Long id) {
        String sql = "SELECT fn_borrado_logico_medicamento(?)";
        jdbcTemplate.update(sql, id);
    }
}