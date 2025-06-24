**Perfulandia - Proyecto Full Stack**

**Ramo**: Full Stack  
**Profesor**: Atanacio Mauricio Montano
**Integrantes**:  
- Ignacio Escalona  
- Máximo García  
- David Coliqueo  

---
**Descripción del Proyecto**

Perfulandia es un sistema backend desarrollado en Spring Boot para la gestión de una tienda de perfumes. Implementa una arquitectura de microservicios con operaciones CRUD completas, documentación de API, pruebas unitarias, e integración con base de datos MySQL.

---
**Tecnologías utilizadas**

-Java 17
-Spring Boot 3.4.5
-Spring Data JPA
-Spring Web
-MySQL
-JUnit 5
-Mockito
-Swagger (OpenAPI)
-Lombok
-Maven
---

**Estructura del proyecto**

- /src/main/java/com/Perfulandia/perfulandia/model: Entidades JPA

- /src/main/java/com/Perfulandia/perfulandia/repository: Repositorios JPA

- /src/main/java/com/Perfulandia/perfulandia/service: Servicios de negocio

- /src/main/java/com/Perfulandia/perfulandia/controller: Controladores REST

- /src/test/java/com/Perfulandia/perfulandia/: Pruebas unitarias (Service y Controller)

- application.properties: Configuración de base de datos MySQL
  
---
**Entidades del sistema**

- Producto
- Pedido
- Usuario
- Sucursal
- Envío
  
---
**Documentación de la API**

La documentación se encuentra disponible mediante Swagger UI:

- http://localhost:8080/swagger-ui/index.html

---
**Configuracion application.properties**

spring.application.name=perfulandia
springdoc.api-docs.enabled=true
springdoc.swagger-ui.enabled=true
springdoc.swagger-ui.path=/doc/swagger-ui.html
spring.profiles.active=dev
spring.datasource.url=jdbc:mysql://localhost:3306/db_perfulandia
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

---
