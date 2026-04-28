CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    rol VARCHAR(20) NOT NULL
);

CREATE TABLE tipo_vehiculo (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE vehiculo (
    id SERIAL PRIMARY KEY,
    placa VARCHAR(6) NOT NULL,
    hora_entrada TIMESTAMP NOT NULL,
    hora_salida TIMESTAMP,
    ubicacion VARCHAR(20),
    tipo_vehiculo_id INTEGER,
    CONSTRAINT fk_tipo_vehiculo
        FOREIGN KEY(tipo_vehiculo_id)
        REFERENCES tipo_vehiculo(id)
);
