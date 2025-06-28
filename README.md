# 📚 BACKEND – Sistema de Gestión de Biblioteca

## Requisitos Generales

- Utilizar Spring Boot 3+.
- Seguir una arquitectura por capas: `Entity`, `DTO`, `Repository`, `Service`, `Controller`.
- Usar Spring Data JPA.
- Base de datos: H2 en memoria o PostgreSQL (a elección).
- Validación con anotaciones de Jakarta Bean Validation (`@NotNull`, `@NotBlank`, etc.).
- Manejo correcto de errores: debe devolver respuestas claras y con los códigos HTTP adecuados.
- ⚠️ **Muy importante**: se debe **respetar estrictamente el contrato definido** a continuación, tanto en el _formato de
  las requests y responses_, como en las _URLs de los endpoints_. Cualquier desviación puede invalidar la integración
  del sistema.
- **opcional:** Usar `ModelMapper` para convertir entre entidades y DTOs.

---

## 📄 Entidades

### Usuario

| Campo           | Tipo   | Obligatorio |
| --------------- | ------ | ----------- |
| id              | Long   | No          |
| nombre_completo | String | Sí          |
| email           | String | Sí          |
| telefono        | String | No          |

**Validaciones**:

- `nombreCompleto`: no debe ser nulo ni estar en blanco.
- `email`: no debe ser nulo, no estar en blanco y debe tener formato de email válido.

---

### Libro

| Campo       | Tipo   | Obligatorio |
| ----------- | ------ | ----------- |
| id          | Long   | No          |
| titulo      | String | Sí          |
| autor       | String | Sí          |
| isbn        | String | Sí          |
| categoria   | String | Sí          |
| disponible  | Boolean| Sí          |

**Validaciones**:

- `titulo`: no debe ser nulo ni estar en blanco.
- `autor`: no debe ser nulo ni estar en blanco.
- `isbn`: no debe ser nulo ni estar en blanco.
- `categoria`: no debe ser nulo ni estar en blanco.
- `disponible`: por defecto debe ser `true`.

---

### Prestamo

| Campo         | Tipo      | Obligatorio |
| ------------- | --------- | ----------- |
| id            | Long      | No          |
| usuario       | Usuario   | Sí          |
| libro         | Libro     | Sí          |
| fechaPrestamo | LocalDate | Sí          |
| fechaDevolucion| LocalDate| No          |
| estado        | String    | Sí          |

**Validaciones**:

- `usuario` y `libro` deben estar correctamente registrados (existir en la base).
- `fechaPrestamo` debe ser una fecha válida y no nula.
- `estado` debe ser uno de: "ACTIVO", "DEVUELTO", "VENCIDO".
- `fechaDevolucion` es opcional (se completa cuando se devuelve el libro).

---

### data.sql

```sql
INSERT INTO libros (id, titulo, autor, isbn, categoria, disponible)
VALUES (1, 'Cien años de soledad', 'Gabriel García Márquez', '978-84-376-0494-7', 'Literatura', true),
       (2, 'El principito', 'Antoine de Saint-Exupéry', '978-84-261-4059-7', 'Infantil', true),
       (3, 'Don Quijote de la Mancha', 'Miguel de Cervantes', '978-84-376-0815-0', 'Clásicos', false),
       (4, 'Fundación', 'Isaac Asimov', '978-84-450-7514-4', 'Ciencia Ficción', true);

INSERT INTO usuarios (id, nombre_completo, email, telefono)
VALUES (1, 'Ana Rodríguez', 'ana.rodriguez@email.com', '555-0101'),
       (2, 'Carlos Mendoza', 'carlos.mendoza@email.com', '555-0102'),
       (3, 'María Fernández', 'maria.fernandez@email.com', '555-0103'),
       (4, 'Pedro Sánchez', 'pedro.sanchez@email.com', '555-0104');

INSERT INTO prestamos (id, fecha_prestamo, fecha_devolucion, estado, usuario_id, libro_id)
VALUES (100, '2024-01-15', '2024-01-29', 'DEVUELTO', 1, 1),
       (101, '2024-02-01', NULL, 'ACTIVO', 2, 3),
       (102, '2024-02-10', '2024-02-24', 'DEVUELTO', 3, 2),
       (103, '2024-02-20', NULL, 'VENCIDO', 4, 4);
```

---

# 📡 ENDPOINTS A IMPLEMENTAR

Este documento especifica los endpoints REST que deben desarrollarse para el sistema de gestión de biblioteca. Es fundamental respetar:

- Las **rutas exactas**.
- El **formato de las requests y responses**.
- El **manejo de errores adecuado**.
- El uso correcto de los **tipos de datos y validaciones**.

---

## 👥 Usuarios

### `GET /api/v1/usuarios`

- Devuelve todos los usuarios registrados.
- **Response**: lista de objetos con los campos `id`, `nombre_completo`, `email`, y `telefono`.

#### Ejemplo de respuesta

```json
[
  {
    "id": 1,
    "nombre_completo": "Ana Rodríguez",
    "email": "ana.rodriguez@email.com",
    "telefono": "555-0101"
  },
  {
    "id": 2,
    "nombre_completo": "Carlos Mendoza",
    "email": "carlos.mendoza@email.com",
    "telefono": "555-0102"
  }
]
```

---

## 📖 LIBROS

### `GET /api/v1/libros`

- Devuelve una lista de libros.
- **Parámetros opcionales (query params):**
  - `disponible` (Boolean): filtra los libros por disponibilidad.
  - `categoria` (String): filtra los libros por categoría.

#### Ejemplo de respuesta

```json
[
  {
    "id": 1,
    "titulo": "Cien años de soledad",
    "autor": "Gabriel García Márquez",
    "isbn": "978-84-376-0494-7",
    "categoria": "Literatura",
    "disponible": true
  },
  {
    "id": 2,
    "titulo": "El principito",
    "autor": "Antoine de Saint-Exupéry",
    "isbn": "978-84-261-4059-7",
    "categoria": "Infantil",
    "disponible": true
  }
]
```

---

## 📋 Listar PRÉSTAMOS

### `GET /api/v1/prestamos`

- **Parámetros opcionales (query params):**
  - `usuario_id` (Long): filtra los préstamos por ID del usuario.
  - `libro_id` (Long): filtra los préstamos por ID del libro.
  - `estado` (String): filtra los préstamos por estado ("ACTIVO", "DEVUELTO", "VENCIDO").

#### Ejemplo de respuesta

```json
[
  {
    "id": 101,
    "usuario": {
      "id": 2,
      "nombre_completo": "Carlos Mendoza",
      "email": "carlos.mendoza@email.com",
      "telefono": "555-0102"
    },
    "libro": {
      "id": 3,
      "titulo": "Don Quijote de la Mancha",
      "autor": "Miguel de Cervantes",
      "isbn": "978-84-376-0815-0",
      "categoria": "Clásicos",
      "disponible": false
    },
    "fecha_prestamo": "2024-02-01",
    "fecha_devolucion": null,
    "estado": "ACTIVO"
  }
]
```

---

## ➕ Crear PRÉSTAMO

### `POST /api/v1/prestamos`

### 🔸 Request Body – JSON esperado

```json
{
  "usuario_id": 1,
  "libro_id": 2,
  "fecha_prestamo": "2024-03-01"
}
```

### 🔸 Response exitosa

```json
{
  "id": 105,
  "usuario": {
    "id": 1,
    "nombre_completo": "Ana Rodríguez",
    "email": "ana.rodriguez@email.com",
    "telefono": "555-0101"
  },
  "libro": {
    "id": 2,
    "titulo": "El principito",
    "autor": "Antoine de Saint-Exupéry",
    "isbn": "978-84-261-4059-7",
    "categoria": "Infantil",
    "disponible": false
  },
  "fecha_prestamo": "2024-03-01",
  "fecha_devolucion": null,
  "estado": "ACTIVO"
}
```

---

## 🔄 Devolver LIBRO

### `PUT /api/v1/prestamos/{id}/devolver`

- Marca un préstamo como devuelto.
- Actualiza el estado del préstamo a "DEVUELTO".
- Establece la fecha de devolución como la fecha actual.
- Marca el libro como disponible nuevamente.

### 🔸 Response exitosa

```json
{
  "id": 105,
  "usuario": {
    "id": 1,
    "nombre_completo": "Ana Rodríguez",
    "email": "ana.rodriguez@email.com",
    "telefono": "555-0101"
  },
  "libro": {
    "id": 2,
    "titulo": "El principito",
    "autor": "Antoine de Saint-Exupéry",
    "isbn": "978-84-261-4059-7",
    "categoria": "Infantil",
    "disponible": true
  },
  "fecha_prestamo": "2024-03-01",
  "fecha_devolucion": "2024-03-15",
  "estado": "DEVUELTO"
}
```

---

## ❗ MANEJO DE ERRORES

Es **obligatorio** implementar un manejo adecuado y estructurado de los errores en el backend. Todos los errores deben
estar claramente definidos, y las respuestas deben:

- Ser en formato **JSON**.
- Incluir un **código de estado HTTP apropiado**.
- Proporcionar un **mensaje claro y específico**.

---

### 🔹 Requisitos generales

- No devolver errores sin procesar ni stacktraces al usuario.
- Toda respuesta de error debe ser en **formato JSON**.

---

### 🔸 Códigos HTTP esperados

| Código | Situación                                                                  |
| ------ | -------------------------------------------------------------------------- |
| 400    | Datos inválidos en la request (campos faltantes, mal formato, etc.)       |
| 404    | Recurso no encontrado (ej: usuario, libro o préstamo inexistente)         |
| 409    | Conflicto (ej: intentar prestar un libro ya prestado)                     |
| 500    | Error interno del servidor (excepción no controlada)                      |

---

### 🔸 Ejemplos de errores

#### Error 400 – Datos inválidos

```json
{
  "status": 400,
  "message": "El campo 'email' debe tener un formato válido"
}
```

#### Error 404 – Recurso no encontrado

```json
{
  "status": 404,
  "message": "Usuario con ID 999 no encontrado"
}
```

#### Error 409 – Conflicto

```json
{
  "status": 409,
  "message": "El libro ya está prestado y no está disponible"
}
```

#### Error 500 – Error interno

```json
{
  "status": 500,
  "message": "Ocurrió un error inesperado al procesar la solicitud"
}
```

---

## 🧪 TESTING

Se requiere incluir **tests automatizados** para verificar el correcto funcionamiento de los endpoints desarrollados.

---

### 🔹 Alcance mínimo obligatorio

- **Tests unitarios** para:
  - **Servicios** (`@Service`)
  - **Controllers** (`@RestControllers`)
- **Tests de integración** para:
  - Endpoints principales
  - Validaciones de negocio (ej: no prestar libro ya prestado)

---

## ✅ CRITERIOS DE EVALUACIÓN POR ENDPOINT

El sistema será evaluado en función del correcto funcionamiento y estructura de los endpoints solicitados. Cada uno debe
respetar el contrato de API especificado (rutas, métodos, formato JSON y manejo de errores).

| Endpoint                          | Puntos |
|-----------------------------------| ------ |
| `GET /api/v1/usuarios`            | 10     |
| `GET /api/v1/libros`              | 15     |
| `GET /api/v1/prestamos`           | 25     |
| `POST /api/v1/prestamos`          | 25     |
| `PUT /api/v1/prestamos/{id}/devolver` | 15     |
| Testing                           | 15     |
| Manejo adecuado de errores        | 10     |

**Total: 115 puntos**

---

### 📌 Notas adicionales

- Se evaluará el cumplimiento exacto de:
  - La **ruta** del endpoint.
  - El **método HTTP** utilizado.
  - El **formato del JSON** de entrada y salida.
  - Las **validaciones** requeridas.
  - El **manejo correcto de errores** con códigos HTTP y mensajes claros.
- Se penalizará si se modifica el contrato de la API (por ejemplo, cambiar nombres de campos, rutas o
  estructuras de datos).
- **Validaciones de negocio importantes**:
  - No permitir prestar un libro que ya está prestado.
  - Al crear un préstamo, marcar el libro como no disponible.
  - Al devolver un libro, marcarlo como disponible nuevamente.
- La claridad del código y la separación en capas también influirá en la nota final.

---

### 🎯 Reglas de Negocio Específicas

1. **Préstamo de libros**: Solo se pueden prestar libros que estén disponibles (`disponible = true`).
2. **Estado de préstamos**: Los préstamos se crean con estado "ACTIVO".
3. **Devolución**: Al devolver un libro, el estado cambia a "DEVUELTO" y se establece la fecha de devolución.
4. **Disponibilidad**: Cuando se presta un libro, se marca como no disponible. Cuando se devuelve, se marca como disponible.
5. **Validación de fechas**: La fecha de préstamo no puede ser posterior a la fecha actual.
