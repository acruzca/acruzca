# Gestión de Prado Navas

Backend en Java con Spring Boot y MySQL para registrar y administrar prados, utilizando Bootstrap 5 y el tema AdminLTE 4 para la interfaz basada en Thymeleaf.

## Características

- CRUD completo de prados (nombre, ubicación, extensión en hectáreas, estado de conservación y fecha de registro).
- Plantillas Thymeleaf estilizadas con Bootstrap 5 y AdminLTE 4.
- Persistencia con Spring Data JPA y MySQL.
- Validaciones de formularios con Bean Validation.
- Carga de datos de ejemplo mediante `data.sql`.

## Requisitos previos

- Java 17 o superior.
- Maven 3.9 o superior.
- Servidor MySQL accesible con una base de datos creada (por ejemplo `pradonavas_db`).

## Configuración

Edita `src/main/resources/application.properties` con las credenciales y URL de tu instancia MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/pradonavas_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=pradonavas_user
spring.datasource.password=tu_clave_segura
```

Opcionalmente puedes ajustar `spring.jpa.hibernate.ddl-auto` según tu estrategia de migraciones.

## Ejecución

1. Instala las dependencias y compila el proyecto:
   ```bash
   mvn clean package
   ```
2. Arranca la aplicación:
   ```bash
   mvn spring-boot:run
   ```
3. Abre `http://localhost:8080/pradonavas` en tu navegador para acceder al listado y formularios.

Las plantillas ya incluyen los recursos de AdminLTE 4 y Bootstrap desde CDN, por lo que no se requiere configuración adicional para los estilos.

## Pruebas

El perfil `test` utiliza una base de datos en memoria H2 para ejecutar las pruebas unitarias. Puedes ejecutarlas con:

```bash
mvn test
```

## Estructura principal del proyecto

```
src/
 ├─ main/
 │   ├─ java/com/acruzca/pradonavas
 │   │   ├─ controller/   # Controladores web
 │   │   ├─ model/        # Entidades JPA
 │   │   ├─ repository/   # Repositorios Spring Data
 │   │   └─ service/      # Lógica de negocio
 │   └─ resources/
 │       ├─ templates/    # Vistas Thymeleaf con AdminLTE
 │       ├─ application.properties
 │       └─ data.sql      # Datos de ejemplo
 └─ test/                 # Pruebas con perfil H2
```

¡Listo! Con esto tendrás una base sólida para gestionar prados y seguir ampliando la funcionalidad según tus necesidades.
