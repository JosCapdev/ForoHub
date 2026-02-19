# ForoHub

API REST desarrollada en **Java con Spring Boot** como parte del *Challenge Back End* de Alura Latam.  
El objetivo es implementar un foro donde se puedan **crear, listar, actualizar y eliminar tópicos**, con seguridad y autenticación mediante **Spring Security y JWT**.

## 🚀 Tecnologías utilizadas
- Java 17  
- Spring Boot  
- Spring Data JPA  
- Spring Security  
- JWT (JSON Web Token)  
- Maven  
- Base de datos relacional (MySQL)
- Flyway (migraciones de base de datos)
- Lombok (reducción de boilerplate en entidades y servicios)
- Spring Validation (validaciones de datos de entrada)
- SpringDoc OpenAPI (documentación automática de la API)  

## 📌 Funcionalidades principales
- **CRUD de Tópicos**: crear, listar, detallar, actualizar y eliminar tópicos.  
- **Gestión de Usuarios**: registro y autenticación.  
- **Perfiles y Cursos**: entidades relacionadas para enriquecer la lógica del foro.  
- **Autenticación y Autorización**: seguridad implementada con Spring Security y JWT.  
- **Borrado lógico**: los registros no se eliminan físicamente, se marcan como inactivos.  
- **Paginación y ordenación**: endpoints preparados para devolver resultados paginados.  

## 📂 Estructura del proyecto
- `controller/` → Endpoints REST.  
- `service/impl/` → Lógica de negocio.  
- `domain/` → Entidades del modelo.  
- `repository/` → Interfaces de acceso a datos.  

## 🗄️ Base de datos
El proyecto incluye un **diagrama de base de datos** con las entidades principales:  
- Usuario  
- Perfil  
- Curso  
- Tópico  
- Respuesta  

## 🔑 Seguridad
- Registro y login de usuarios.  
- Generación de token JWT.  
- Validación de token en cada request.  
- Roles y perfiles para controlar acceso a endpoints.

## 🗑️ Estrategia de borrado

- **Usuarios, Tópicos y Cursos**: se implementa borrado lógico (soft delete) para preservar historial y relaciones.   
- **Perfiles y Respuestas**: se aplica borrado físico (hard delete), dado que no es necesario conservar registros eliminados.


## 📖 Cómo ejecutar
1. Clonar el repositorio:  
   ```bash
   git clone https://github.com/JosCapdev/ForoHub.git
   ```
2. Configurar la base de datos en application.properties.

3. Ejecutar con Maven:
```bash
mvn spring-boot:run
```
4. Acceder a la API en:
 ```Código
http://localhost:8080
 ```
5. Acceder a la documentación interactiva (Swagger UI):
```UI
http://localhost:8080/swagger-ui.html
```
## ✅ Endpoints principales
POST /login → Autenticación y generación de token.

POST /topicos → Crear un nuevo tópico.

GET /topicos → Listar tópicos (paginados).

GET /topicos/{id} → Detallar un tópico.

PUT /topicos/{id} → Actualizar un tópico.

DELETE /topicos/{id} → Borrado lógico de un tópico.

## 📌 Challenge
Este proyecto corresponde al Foro Hub Challenge Back End de Alura Latam.

## 👨‍💻 Autor
**JosCapdev**  
[GitHub](https://github.com/JosCapdev)  
