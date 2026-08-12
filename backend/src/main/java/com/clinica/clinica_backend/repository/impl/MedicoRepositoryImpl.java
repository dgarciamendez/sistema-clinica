package com.clinica.clinica_backend.repository.impl;

import com.clinica.clinica_backend.model.Medico;
import com.clinica.clinica_backend.repository.MedicoRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MedicoRepositoryImpl implements MedicoRepository {

    private final JdbcTemplate jdbcTemplate;

    public MedicoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Medico> findAll() {
        String sql = "SELECT * FROM fn_obtener_todos_medicos()";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Medico.class));
    }

    @Override
    public Optional<Medico> findById(Long id) {
        String sql = "SELECT * FROM fn_obtener_medico_por_id(?)";
        List<Medico> resultados = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Medico.class), id);
        return resultados.stream().findFirst();
    }

    @Override
    public Medico save(Medico medico) {
        String sql = "SELECT * FROM fn_insertar_medico(?, ?, ?, ?, ?)";
        Long idGenerado = jdbcTemplate.queryForObject(sql, Long.class,
                medico.getNombre(),
                medico.getApellido(),
                medico.getEspecialidad(),
                medico.getTelefono(),
                medico.getEmail());
        medico.setIdMedico(idGenerado);
        return medico;
    }

    @Override
    public void update(Medico medico) {
        String sql = "SELECT fn_actualizar_medico(?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                medico.getIdMedico(),
                medico.getNombre(),
                medico.getApellido(),
                medico.getEspecialidad(),
                medico.getTelefono(),
                medico.getEmail());
    }

    @Override
    public void deleteById(Long id) {
        String sql = "SELECT fn_borrado_logico_medico(?)";
        jdbcTemplate.update(sql, id);
    }
}