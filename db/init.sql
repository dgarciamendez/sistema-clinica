-- ============================================================
-- FUNCIONES PARA CRUD DE PACIENTES
-- ============================================================

-- Obtener todos los pacientes activos
CREATE OR REPLACE FUNCTION fn_obtener_todos_pacientes()
RETURNS SETOF pacientes
LANGUAGE plpgsql
AS $$
BEGIN
    RETURN QUERY SELECT * FROM pacientes WHERE activo = true ORDER BY id_paciente;
END;
$$;

-- Obtener un paciente por ID (sin importar si está activo o no, para poder actualizar/eliminar)
CREATE OR REPLACE FUNCTION fn_obtener_paciente_por_id(p_id BIGINT)
RETURNS pacientes
LANGUAGE plpgsql
AS $$
DECLARE
    v_paciente pacientes%ROWTYPE;
BEGIN
    SELECT * INTO v_paciente FROM pacientes WHERE id_paciente = p_id;
    IF NOT FOUND THEN
        RAISE EXCEPTION 'Paciente con ID % no encontrado', p_id;
    END IF;
    RETURN v_paciente;
END;
$$;

-- Insertar un nuevo paciente (devuelve el ID generado)
CREATE OR REPLACE FUNCTION fn_insertar_paciente(
    p_nombre VARCHAR(100),
    p_apellido VARCHAR(100),
    p_email VARCHAR(100),
    p_fecha_nacimiento DATE,
    p_genero CHAR(1),
    p_direccion VARCHAR(255),
    p_telefono VARCHAR(20)
)
RETURNS BIGINT
LANGUAGE plpgsql
AS $$
DECLARE
    v_id BIGINT;
BEGIN
    -- Validar que el email no exista ya (opcional, pero evita duplicados)
    IF EXISTS (SELECT 1 FROM pacientes WHERE email = p_email) THEN
        RAISE EXCEPTION 'El email % ya está registrado', p_email;
    END IF;

    INSERT INTO pacientes (nombre, apellido, email, fecha_nacimiento, genero, direccion, telefono)
    VALUES (p_nombre, p_apellido, p_email, p_fecha_nacimiento, p_genero, p_direccion, p_telefono)
    RETURNING id_paciente INTO v_id;

    RETURN v_id;
END;
$$;

-- Actualizar un paciente existente
CREATE OR REPLACE FUNCTION fn_actualizar_paciente(
    p_id BIGINT,
    p_nombre VARCHAR(100),
    p_apellido VARCHAR(100),
    p_email VARCHAR(100),
    p_fecha_nacimiento DATE,
    p_genero CHAR(1),
    p_direccion VARCHAR(255),
    p_telefono VARCHAR(20)
)
RETURNS VOID
LANGUAGE plpgsql
AS $$
BEGIN
    -- Verificar que el paciente exista
    IF NOT EXISTS (SELECT 1 FROM pacientes WHERE id_paciente = p_id) THEN
        RAISE EXCEPTION 'Paciente con ID % no encontrado', p_id;
    END IF;

    -- Validar que el email no esté siendo usado por otro paciente
    IF EXISTS (SELECT 1 FROM pacientes WHERE email = p_email AND id_paciente != p_id) THEN
        RAISE EXCEPTION 'El email % ya está registrado por otro paciente', p_email;
    END IF;

    UPDATE pacientes
    SET nombre = p_nombre,
        apellido = p_apellido,
        email = p_email,
        fecha_nacimiento = p_fecha_nacimiento,
        genero = p_genero,
        direccion = p_direccion,
        telefono = p_telefono
    WHERE id_paciente = p_id;
END;
$$;

-- Borrado lógico de un paciente
CREATE OR REPLACE FUNCTION fn_borrado_logico_paciente(p_id BIGINT)
RETURNS VOID
LANGUAGE plpgsql
AS $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pacientes WHERE id_paciente = p_id) THEN
        RAISE EXCEPTION 'Paciente con ID % no encontrado', p_id;
    END IF;

    UPDATE pacientes SET activo = false WHERE id_paciente = p_id;
END;
$$;


-- ============================================================
-- FUNCIONES PARA CRUD DE MÉDICOS
-- ============================================================

CREATE OR REPLACE FUNCTION fn_obtener_todos_medicos()
RETURNS SETOF medicos
LANGUAGE plpgsql
AS $$
BEGIN
    RETURN QUERY SELECT * FROM medicos WHERE activo = true ORDER BY id_medico;
END;
$$;

CREATE OR REPLACE FUNCTION fn_obtener_medico_por_id(p_id BIGINT)
RETURNS medicos
LANGUAGE plpgsql
AS $$
DECLARE
    v_medico medicos%ROWTYPE;
BEGIN
    SELECT * INTO v_medico FROM medicos WHERE id_medico = p_id;
    IF NOT FOUND THEN
        RAISE EXCEPTION 'Médico con ID % no encontrado', p_id;
    END IF;
    RETURN v_medico;
END;
$$;

CREATE OR REPLACE FUNCTION fn_insertar_medico(
    p_nombre VARCHAR(100),
    p_apellido VARCHAR(100),
    p_especialidad VARCHAR(100),
    p_telefono VARCHAR(20),
    p_email VARCHAR(100)
)
RETURNS BIGINT
LANGUAGE plpgsql
AS $$
DECLARE
    v_id BIGINT;
BEGIN
    IF EXISTS (SELECT 1 FROM medicos WHERE email = p_email) THEN
        RAISE EXCEPTION 'El email % ya está registrado', p_email;
    END IF;

    INSERT INTO medicos (nombre, apellido, especialidad, telefono, email)
    VALUES (p_nombre, p_apellido, p_especialidad, p_telefono, p_email)
    RETURNING id_medico INTO v_id;

    RETURN v_id;
END;
$$;

CREATE OR REPLACE FUNCTION fn_actualizar_medico(
    p_id BIGINT,
    p_nombre VARCHAR(100),
    p_apellido VARCHAR(100),
    p_especialidad VARCHAR(100),
    p_telefono VARCHAR(20),
    p_email VARCHAR(100)
)
RETURNS VOID
LANGUAGE plpgsql
AS $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM medicos WHERE id_medico = p_id) THEN
        RAISE EXCEPTION 'Médico con ID % no encontrado', p_id;
    END IF;

    IF EXISTS (SELECT 1 FROM medicos WHERE email = p_email AND id_medico != p_id) THEN
        RAISE EXCEPTION 'El email % ya está registrado por otro médico', p_email;
    END IF;

    UPDATE medicos
    SET nombre = p_nombre,
        apellido = p_apellido,
        especialidad = p_especialidad,
        telefono = p_telefono,
        email = p_email
    WHERE id_medico = p_id;
END;
$$;

CREATE OR REPLACE FUNCTION fn_borrado_logico_medico(p_id BIGINT)
RETURNS VOID
LANGUAGE plpgsql
AS $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM medicos WHERE id_medico = p_id) THEN
        RAISE EXCEPTION 'Médico con ID % no encontrado', p_id;
    END IF;
    UPDATE medicos SET activo = false WHERE id_medico = p_id;
END;
$$;


-- ============================================================
-- FUNCIONES PARA CRUD DE MEDICAMENTOS
-- ============================================================

CREATE OR REPLACE FUNCTION fn_obtener_todos_medicamentos()
RETURNS SETOF medicamentos
LANGUAGE plpgsql
AS $$
BEGIN
    RETURN QUERY SELECT * FROM medicamentos WHERE activo = true ORDER BY id_medicamento;
END;
$$;

CREATE OR REPLACE FUNCTION fn_obtener_medicamento_por_id(p_id BIGINT)
RETURNS medicamentos
LANGUAGE plpgsql
AS $$
DECLARE
    v_medicamento medicamentos%ROWTYPE;
BEGIN
    SELECT * INTO v_medicamento FROM medicamentos WHERE id_medicamento = p_id;
    IF NOT FOUND THEN
        RAISE EXCEPTION 'Medicamento con ID % no encontrado', p_id;
    END IF;
    RETURN v_medicamento;
END;
$$;

CREATE OR REPLACE FUNCTION fn_insertar_medicamento(
    p_nombre VARCHAR(150),
    p_descripcion TEXT,
    p_stock INTEGER,
    p_precio_unitario DECIMAL(12,2),
    p_fecha_caducidad DATE
)
RETURNS BIGINT
LANGUAGE plpgsql
AS $$
DECLARE
    v_id BIGINT;
BEGIN
    INSERT INTO medicamentos (nombre, descripcion, stock, precio_unitario, fecha_caducidad)
    VALUES (p_nombre, p_descripcion, p_stock, p_precio_unitario, p_fecha_caducidad)
    RETURNING id_medicamento INTO v_id;

    RETURN v_id;
END;
$$;

CREATE OR REPLACE FUNCTION fn_actualizar_medicamento(
    p_id BIGINT,
    p_nombre VARCHAR(150),
    p_descripcion TEXT,
    p_stock INTEGER,
    p_precio_unitario DECIMAL(12,2),
    p_fecha_caducidad DATE
)
RETURNS VOID
LANGUAGE plpgsql
AS $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM medicamentos WHERE id_medicamento = p_id) THEN
        RAISE EXCEPTION 'Medicamento con ID % no encontrado', p_id;
    END IF;

    UPDATE medicamentos
    SET nombre = p_nombre,
        descripcion = p_descripcion,
        stock = p_stock,
        precio_unitario = p_precio_unitario,
        fecha_caducidad = p_fecha_caducidad
    WHERE id_medicamento = p_id;
END;
$$;

CREATE OR REPLACE FUNCTION fn_borrado_logico_medicamento(p_id BIGINT)
RETURNS VOID
LANGUAGE plpgsql
AS $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM medicamentos WHERE id_medicamento = p_id) THEN
        RAISE EXCEPTION 'Medicamento con ID % no encontrado', p_id;
    END IF;
    UPDATE medicamentos SET activo = false WHERE id_medicamento = p_id;
END;
$$;