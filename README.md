# Parcial2-SergioPuentes
# 🚗 Sistema de Gestión de Parqueadero

Aplicación web para la gestión de entradas y salidas de vehículos en un parqueadero, con control de acceso basado en roles:

* 👨‍💼 ADMINISTRADOR
* 🚗 ACOMODADOR
* 👀 CLIENTE

---

# 🧱 Tecnologías utilizadas

## 🔙 Backend

* Java + Spring Boot
* Spring Security (autenticación por sesión)
* Spring Data JPA (Hibernate)
* Lombok
* Swagger (documentación API)

## 🎨 Frontend

* HTML
* CSS
* JavaScript (Fetch API)

## 🗄️ Base de datos

* PostgreSQL

---

# 📁 Estructura del proyecto

```
parqueadero/
├── src/main/java/com/parqueadero/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── model/
│   ├── security/
│   └── config/
│
├── src/main/resources/
│   ├── static/        # Frontend (HTML, CSS, JS)
│   └── application.properties
```

---

# 🔐 Roles del sistema

## 👨‍💼 ADMINISTRADOR

Puede:

* Crear vehículos (registrar entrada)
* Editar información
* Registrar salida
* Eliminar registros
* Gestionar tipos de vehículo
* Gestionar usuarios

---

## 🚗 ACOMODADOR

Puede:

* Ver vehículos
* Actualizar ubicación

---

## 👀 CLIENTE

Puede:

* Solo visualizar vehículos

---

# 🗄️ Diseño de Base de Datos

## 📌 Tabla: usuario

| Campo    | Tipo        | Descripción                          |
| -------- | ----------- | ------------------------------------ |
| id       | SERIAL      | PK                                   |
| username | VARCHAR(50) | Usuario único                        |
| password | VARCHAR     | Contraseña                           |
| rol      | VARCHAR(20) | ADMINISTRADOR / ACOMODADOR / CLIENTE |

---

## 📌 Tabla: tipo_vehiculo

| Campo  | Tipo        | Descripción              |
| ------ | ----------- | ------------------------ |
| id     | SERIAL      | PK                       |
| nombre | VARCHAR(50) | Tipo (Carro, Moto, etc.) |

---

## 📌 Tabla: vehiculo

| Campo            | Tipo        | Descripción      |
| ---------------- | ----------- | ---------------- |
| id               | SERIAL      | PK               |
| placa            | VARCHAR(6)  | Máx 6 caracteres |
| hora_entrada     | TIMESTAMP   | Entrada          |
| hora_salida      | TIMESTAMP   | Salida           |
| ubicacion        | VARCHAR(20) | Ej: A-10         |
| tipo_vehiculo_id | INTEGER     | FK               |

---

## 🔗 Relaciones

* Un vehículo pertenece a un tipo → **ManyToOne**
* Usuarios independientes (solo autenticación)

---

## 🧩 Diagrama lógico

```
tipo_vehiculo (1) ──── (N) vehiculo
```

---

# ⚙️ Configuración

## application.properties

```
spring.datasource.url=jdbc:postgresql://localhost:5432/parqueadero
spring.datasource.username=postgres
spring.datasource.password=1234

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

# ▶️ Ejecución del proyecto

1. Crear base de datos en PostgreSQL:

```
CREATE DATABASE parqueadero;
```

2. Ejecutar la aplicación:

```
mvn spring-boot:run
```

3. Acceder:

* API: http://localhost:8080
* Swagger: http://localhost:8080/swagger-ui.html

---

# 🔑 Usuarios de prueba

```
admin / 1234
acomodador / 1234
cliente / 1234
```

---

# 📡 API REST (Ejemplos)

## 🔹 Crear vehículo

POST `/admin/vehiculos`

```
{
  "placa": "ABC123",
  "ubicacion": "A-10",
  "tipoVehiculo": {
    "id": 1
  }
}
```

---

## 🔹 Registrar salida

PATCH `/admin/vehiculos/{id}/salida`

---

## 🔹 Actualizar ubicación (Acomodador)

PATCH `/acomodador/vehiculos/{id}/ubicacion`

```
"A-15"
```

---

# 🖥️ Frontend

El frontend se encuentra en:

```
/src/main/resources/static/
```

---

## 📄 login.html

```html
<form method="post" action="/login">
  <input type="text" name="username" placeholder="Usuario"/>
  <input type="password" name="password" placeholder="Contraseña"/>
  <button>Ingresar</button>
</form>
```

---

## 📄 admin.html

```html
<h1>Panel Administrador</h1>
<button onclick="listar()">Ver Vehículos</button>

<script>
function listar(){
  fetch('/admin/vehiculos')
    .then(r => r.json())
    .then(data => console.log(data))
}
</script>
```

---

## 📄 acomodador.html

```html
<h1>Acomodador</h1>
<script>
fetch('/acomodador/vehiculos')
  .then(r => r.json())
  .then(data => console.log(data))
</script>
```

---

## 📄 cliente.html

```html
<h1>Cliente</h1>
<script>
fetch('/cliente/vehiculos')
  .then(r => r.json())
  .then(data => console.log(data))
</script>
```

---

# 🔐 Seguridad

* Login con formulario personalizado
* Control de acceso por roles:

  * `/admin/**`
  * `/acomodador/**`
  * `/cliente/**`
* Manejo de sesión con Spring Security

---

# 📘 Swagger

Documentación disponible en:

```
http://localhost:8080/swagger-ui.html
```

Permite:

* Ver endpoints
* Probar servicios
* Validar requests

---

# ⚠️ Validaciones implementadas

* Placa máximo 6 caracteres
* Hora de salida solo cuando el vehículo sale
* Restricción de roles

---

# 🚀 Mejoras futuras

* DTOs y arquitectura por capas
* Encriptación de contraseñas (BCrypt)
* Cálculo automático de tarifas
* Reportes (PDF / Excel)
* UI con Bootstrap
* Deploy en la nube

---

# 👨‍💻 Autor

Parcial 2 PW - Sistema de Parqueadero
Sergio Andres Puentes Cicery - 20242228195
*Readme creado con la ayuda de ChatGPT

---
