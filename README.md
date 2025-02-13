# Administración de Estudiantes

Este proyecto es un sistema de administración de estudiantes desarrollado con Spring Boot que proporciona operaciones CRUD y búsquedas específicas a través de endpoints REST.

## Requisitos Previos

- Java JDK 17
- Maven 3.8+
- MySQL 8.0+
- Postman (para pruebas de API)

## Configuración de Base de Datos

1. Crear una base de datos MySQL llamada `administracion`:
```sql
CREATE DATABASE administracion;
```

2. Configurar las credenciales en `application.properties`:
```properties
spring.datasource.username=root
spring.datasource.password=12345678
```

## Instalación y Ejecución

1. Clonar el repositorio:
```bash
git clone [URL_DEL_REPOSITORIO]
```

2. Navegar al directorio del proyecto:
```bash
cd Administracion-Estudiantes
```

3. Compilar el proyecto:
```bash
mvn clean install
```

4. Ejecutar la aplicación:
```bash
mvn spring-boot:run
```

La aplicación estará disponible en: `http://localhost:9000`

## Endpoints Disponibles

### Operaciones CRUD
- **Listar Estudiantes**
  - GET `/AdminEstudiantesWs/listar`
  - Retorna todos los estudiantes activos

- **Dar de Alta Estudiante**
  - POST `/AdminEstudiantesWs/guardar`
  - Acepta un JSON con los datos del estudiante

- **Editar Estudiante**
  - PUT `/AdminEstudiantesWs/editar`
  - Requiere el ID del estudiante y los datos actualizados

- **Eliminar Estudiante**
  - DELETE `/AdminEstudiantesWs/eliminar`
  - Eliminación lógica (actualiza campo 'eliminado') siempre y cuando su edad sea 20 años o más

### Búsquedas Específicas
- **Buscar por Nombre**
  - POST `/AdminEstudiantesWs/nombre`
  - Body: `{"nombre": "nombre_estudiante"}`

- **Buscar por Matrícula**
  - POST `/AdminEstudiantesWs/matricula`
  - Body: `{"matricula": "matricula_estudiante"}`

- **Buscar por Licenciatura**
  - POST `/AdminEstudiantesWs/licenciatura`
  - Body: `{"licenciatura": "nombre_licenciatura"}`

## Estructura de Datos del Estudiante

```json
{
    "nombre": "Nombre Estudiante",
    "curp": "CURP123456HDFXXX01",
    "matricula": "MAT2024001",
    "fechaNacimiento": "2000-01-01",
    "licenciatura": "Nombre Licenciatura",
    "estadoProcedencia": "Estado",
    "eliminado": false
}
```

## Dependencias Principales

- Spring Boot 3.0.0
- Spring Data JPA
- Spring Web
- MySQL Connector
- Lombok
- Spring Boot DevTools

## Configuración Adicional

El archivo `application.properties` incluye la siguiente configuración:

```properties
spring.application.name=com.mx.EstudiantesAdministracion
server.port=9000
spring.datasource.url=jdbc:mysql://localhost:3306/administracion?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrival=true
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
```

## Colección Postman

Se incluye una colección de Postman con todos los endpoints configurados para pruebas. Importar el archivo JSON proporcionado en Postman para acceder a todos los endpoints preconfigurados.

## Notas Adicionales

- La aplicación implementa eliminación lógica para mantener el histórico de registros
- Los endpoints de búsqueda son case-sensitive
- La base de datos se actualizará automáticamente con nuevas entidades (ddl-auto=update)
- Se recomienda usar Postman para probar los endpoints
