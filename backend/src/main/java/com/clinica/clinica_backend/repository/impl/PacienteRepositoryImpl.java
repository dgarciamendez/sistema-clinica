package com.clinica.clinica_backend.repository.impl;

import com.clinica.clinica_backend.repository.PacienteRepository;
import com.clinica.clinica_backend.model.Paciente;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PacienteRepositoryImpl implements PacienteRepository {

    private final JdbcTemplate jdbcTemplate;

    // Inyección por constructor (la mejor práctica)
    public PacienteRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Paciente> findAll() {
        // Llamas a tu función o consulta de Postgres directamente
        String sql = "SELECT * FROM fn_obtener_todos_pacientes()";

        // BeanPropertyRowMapper mapea automáticamente los campos snake_case a camelCase
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Paciente.class));
    }

    @Override
    public Optional<Paciente> findById(Long id) {
        String sql = "SELECT * FROM fn_obtener_paciente_por_id(?)";

        List<Paciente> resultados = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Paciente.class),
                id);

        return resultados.stream().findFirst();
    }

    @Override
    public Paciente save(Paciente paciente) {
        // Llamas a tu función de inserción en Postgres que recibe los datos y retorna
        // el ID o el registro creado
        String sql = "SELECT * FROM fn_insertar_paciente(?, ?, ?, ?, ?, ?, ?)";

        Long idGenerado = jdbcTemplate.queryForObject(sql, Long.class,
                paciente.getNombre(),
                paciente.getApellido(),
                paciente.getEmail(),
                paciente.getFechaNacimiento(),
                paciente.getGenero(),
                paciente.getDireccion(),
                paciente.getTelefono());

        paciente.setIdPaciente(idGenerado);
        return paciente;
    }

    @Override
    public void update(Paciente paciente) {
        String sql = "SELECT fn_actualizar_paciente(?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                paciente.getIdPaciente(),
                paciente.getNombre(),
                paciente.getApellido(),
                paciente.getEmail(),
                paciente.getFechaNacimiento(),
                paciente.getGenero(),
                paciente.getDireccion(),
                paciente.getTelefono());
    }

    @Override
    public void deleteById(Long id) {
        // Borrado lógico llamando a tu función o directamente al UPDATE seguro
        String sql = "SELECT fn_borrado_logico_paciente(?)";
        jdbcTemplate.update(sql, id);
    }
}