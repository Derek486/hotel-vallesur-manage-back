# Hotel Vallesur - Backend

![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)

## 📌 Descripción del Proyecto

Este repositorio contiene el backend del sistema de gestión de departamentos *Hotel Vallesur*. Implementado con **Spring Boot**, proporciona servicios REST para gestionar usuarios, departamentos, inquilinos, contratos de alquiler y pagos.

> [!NOTE]
> Para más detalles sobre el proyecto completo, visita: [Hotel Vallesur Management Stack](https://github.com/Derek486/hotel-vallesur-manage-stack.git)

## 🚀 Tecnologías Utilizadas

- **Framework:** Spring Boot (Java 17)
- **Base de Datos:** MySQL
- **Seguridad:** Spring Security & JWT
- **ORM:** JPA / Hibernate
- **Validaciones:** Spring Validation

## 📂 Estructura del Proyecto

```bash
src/main/java/com/miempresa/hotel
├── config/             # Configuración de seguridad y autenticación JWT
├── controlador/        # Controladores REST
├── interfaces/         # Interfaces de repositorios JPA
├── interfaceServicio/  # Definiciones de los servicios
├── modelo/            # Modelos de datos (Entidades JPA)
├── schemas/           # Esquemas para request y response
├── servicio/          # Implementación de lógica de negocio
└── HotelApplication.java  # Punto de entrada de la aplicación
```

## 📌 Funcionalidad del Backend

### 🏢 Gestión de Departamentos
✔ Crear, actualizar y eliminar departamentos
✔ Consultar departamentos e inquilinos asociados

### 👥 Gestión de Usuarios y Agentes
✔ Registro, autenticación y modificación de datos de usuario
✔ Administración de agentes inmobiliarios

### 📝 Gestión de Contratos e Inquilinos
✔ Creación, edición y consulta de contratos de alquiler
✔ Registro de inquilinos y asignación a departamentos

### 💰 Gestión de Pagos
✔ Registro de pagos mensuales de alquiler
✔ Consulta y eliminación de pagos

## 🔧 Instalación y Configuración

### 🖥 Requisitos Previos
- JDK 17 o superior
- Maven
- MySQL

### ⚙ Configuración de la Base de Datos
Edita el archivo `application.properties` en `src/main/resources/`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hotel_db
spring.datasource.username=root
spring.datasource.password=tu_contraseña
```

### 🚀 Ejecución del Proyecto

```sh
# Clonar el repositorio
$ git clone https://github.com/Derek486/hotel-vallesur-manage-back.git
$ cd hotel-vallesur-manage-back

# Construir y ejecutar la aplicación
$ mvn spring-boot:run
```

## 📜 API Endpoints

### 📌 Autenticación y Usuarios
```sh
POST   /auth/login          # Iniciar sesión
POST   /auth/register       # Registrar usuario
GET    /auth/user           # Obtener información del usuario
PUT    /auth/user           # Actualizar usuario
PUT    /auth/password       # Cambiar contraseña
```

### 📌 Gestión de Departamentos
```sh
GET    /departamentos                   # Listar departamentos
POST   /departamentos                   # Crear departamento
GET    /departamentos/{id}              # Mostrar departamento
PUT    /departamentos/{id}              # Actualizar departamento
DELETE /departamentos/{id}              # Eliminar departamento
```

### 📌 Gestión de Contratos e Inquilinos
```sh
GET    /contratoalquileres              # Listar contratos
POST   /contratoalquileres              # Crear contrato
PUT    /contratoalquileres/{id}         # Actualizar contrato

GET    /inquilinos                      # Listar inquilinos
POST   /inquilinos                      # Crear inquilino
GET    /inquilinos/{id}                 # Obtener inquilino
PUT    /inquilinos/{id}                 # Actualizar inquilino
DELETE /inquilinos/{id}                 # Eliminar inquilino
```

### 📌 Gestión de Pagos
```sh
GET    /pagoalquiler                    # Listar pagos
POST   /pagoalquiler                    # Crear pago
GET    /pagoalquiler/mostrarPagoAlquiler/{id}  # Obtener pago
PUT    /pagoalquiler/{id}               # Actualizar pago
DELETE /pagoalquiler/{id}               # Eliminar pago
```

## 📜 Dependencias Importantes (POM.xml)

```xml
<dependencies>
    <!-- Spring Boot Core -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    
    <!-- JWT Authentication -->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-api</artifactId>
        <version>0.11.5</version>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-impl</artifactId>
        <version>0.11.5</version>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-jackson</artifactId>
        <version>0.11.5</version>
    </dependency>
    
    <!-- MySQL Connector -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```