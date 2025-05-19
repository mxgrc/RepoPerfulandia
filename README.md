Perfulandia - Proyecto Full Stack

**Ramo**: Full Stack  
**Profesor**: Atanacio Mauricio Montano
**Integrantes**:  
- Ignacio Escalona  
- Máximo García  
- David Coliqueo  

---
Descripción del Proyecto

Perfulandia es una aplicación backend construida con **Spring Boot** que permite gestionar productos, usuarios, pedidos, sucursales y envíos mediante microservicios. Cada microservicio tiene su propia lógica y controladores RESTful.

---
Tecnologías utilizadas

- Java 22
- Spring Boot 3.4.5
- Maven
- MySQL 8.4 (con Laragon)
- Git & GitHub
- Postman

---
 Estructura del proyecto

perfulandia/
├── producto
├── usuario
├── envio
├── pedido
├── sucursal
├── application.properties
└── PerfulandiaApplication.java

markdown
Copiar
Editar

Cada paquete contiene:
- `controller`
- `model`
- `repository`
- `service`

---
Endpoints disponibles

### Envíos (`/api/v1/envios`)
- `GET /` → Lista todos los envíos
- `POST /` → Crea un nuevo envío
- `GET /{id}` → Busca un envío por ID
- `PUT /{id}` → Actualiza un envío
- `DELETE /{id}` → Elimina un envío

(Similar para `productos`, `usuarios`, `pedidos`, `sucursales`...)

---
