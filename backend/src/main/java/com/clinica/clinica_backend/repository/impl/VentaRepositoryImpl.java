package com.clinica.clinica_backend.repository.impl;

import com.clinica.clinica_backend.dto.request.ItemVentaDTO;
import com.clinica.clinica_backend.model.Venta;
import com.clinica.clinica_backend.repository.VentaRepository;
import com.clinica.clinica_backend.util.JsonUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VentaRepositoryImpl implements VentaRepository {

    private final JdbcTemplate jdbcTemplate;
    private final JsonUtils jsonUtils;

    public VentaRepositoryImpl(JdbcTemplate jdbcTemplate, JsonUtils jsonUtils) {
        this.jdbcTemplate = jdbcTemplate;
        this.jsonUtils = jsonUtils;
    }

    @Override
    public Long procesarVenta(Long idPaciente, Long idMedico, List<ItemVentaDTO> items, String observaciones) {
        String jsonItems = jsonUtils.toJson(items);
        String sql = "SELECT procesar_venta(?, ?, ?::jsonb, ?)";
        return jdbcTemplate.queryForObject(sql, Long.class, idPaciente, idMedico, jsonItems, observaciones);
    }

    @Override
    public Optional<Venta> findById(Long id) {
        String sql = "SELECT * FROM ventas WHERE id_venta = ?";
        List<Venta> resultados = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Venta.class), id);
        return resultados.stream().findFirst();
    }

    @Override
    public List<Venta> findAll() {
        String sql = "SELECT * FROM ventas ORDER BY id_venta DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Venta.class));
    }

    @Override
    public void anularVenta(Long idVenta) {
        String sql = "SELECT anular_venta(?)";
        jdbcTemplate.update(sql, idVenta);
    }
}