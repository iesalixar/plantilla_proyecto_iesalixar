En este repositorio centralizaremos toda la información relativa a los **Proyectos de Desarrollo de Aplicaciones Web del I.E.S Alixar**.
Al continuación encontraremos los **apellidos y nombre** del alumno/a junto al **título de su proyecto**. El enlace nos dará acceso al repositorio del proyecto (no a la página GitHub del usuario).

En este repositorio se debe incluir la documentación especificada en [Requerimientos y criterios a seguir en el desarrollo de los proyectos](https://github.com/iesalixar/plantilla_proyecto_iesalixar/wiki/a.---Criterios-comunes-para-todos-los-proyectos), así como las indicaciones que el tutor haya podido ir a realizando a lo largo del desarrollo del mismo.

El desarrollo de toda Aplicación Web requiere seguir un [proceso estructurado](https://github.com/iesalixar/plantilla_proyecto_iesalixar/wiki/w1.--PROCESO-ESTRUCTURADO-PARA-DESARROLLO-DE-APLICACIONES-WEB), este de contenido de la wiki te puede ayudar.

---

**Los párrafos anteriores son informativos y no deben aparecer en el reposotirio de los alumnos.**

---

# FitConnet

#### Curso Escolar 2023-2024

#### Autor: [Daniel Perez Serrano](https://github.com/Dani-Ps)

#### Tutor: [Antonio Gabriel González Casado]([Enlace a su cuenta de gitHub](https://github.com/antonio-gabriel-gonzalez-casado))

#### Fecha de Inicio: 15-03-2024

#### Fecha de Finalización: DD-MM-YYYY

## Breve descripción del proyecto

FitConnet es una aplicación cuyo fin es el ser de una red social que impacte positivamente en la salud de las personas y por tanto en su vida. En ella cada usuario podrá compartir sus entrenamientos, encontrar nuevos amigos incluso invitar a sus entrenos a otros usuarios con el fin de crear una comunidad enfocada en el deporte que junta se retroalimente en este sentido.

## Definición del objetivo de la aplicación

- **¿Qué va a hacer la aplicación?**

  - La aplicación permitirá el registro de usuarios mayores de 18 años, los cuales tendrán su perfil con amigos y entrenamientos, tanto los creados por ellos como los en los que han participado. Además, habrá una página de feed donde podrán ver los últimos entrenamientos que han subido sus amigos.

- **¿Cuál es su atractivo principal?**

  - El eslogan de mi aplicación es "Let's thrive", que se podría traducir como "¡Prosperemos!". El atractivo principal radica en proporcionar una red social alejada del estándar, enfocada en usuarios que desean iniciar actividades deportivas pero no encuentran la suficiente motivación para hacerlo solos. Aquí es donde cobra sentido la aplicación, al construir una comunidad donde compartir entrenamientos facilita el establecimiento de una rutina.

- **¿Qué problema concreto va a resolver?**

  - La aplicación aborda el problema de la falta de una red social similar a Instagram, pero enfocada exclusivamente en compartir entrenamientos. Mientras que en redes sociales como Instagram se pueden realizar acciones similares, también hay una multitud de otros temas y personas con distintos intereses. Con FitConnect, eliminamos ese 'ruido' generado por otras publicaciones de personas con otros intereses y nos centramos en mejorar nuestra rutina de ejercicio.

- **¿Qué necesidad va a cubrir?**
  - La aplicación cubrirá la necesidad de una red social especializada en compartir entrenamientos. Aunque existen algunas aplicaciones para deportes más concretos, estas carecen del componente de construir una comunidad de amigos/seguidores con quienes compartir entrenamientos.

## Estructura del Proyecto

### Carpeta src-api

Esta carpteta contiene el codigo de la APIREST de Fitconnet, aqui se detalla el stack tecnológico usado:

## Stack Tecnológico

El proyecto utiliza las siguientes tecnologías y dependencias:

### Frameworks y Librerías Principales

- **Spring Boot 3.2.3**

  - `spring-boot-starter-data-jpa`: Para la persistencia de datos usando JPA.
  - `spring-boot-starter-security`: Para la seguridad de la aplicación.
  - `spring-boot-starter-web`: Para la creación de servicios web RESTful.
  - `spring-boot-devtools`: Herramientas de desarrollo para mejorar la productividad (opcional).

- **Java 21**: Versión del JDK utilizada.

### Base de Datos

- **MySQL**
  - `mysql-connector-java`: Conector JDBC para MySQL, versión 8.0.23.

### Seguridad y Autenticación

- **JSON Web Token (JWT)**
  - `jjwt-api`, `jjwt-impl`, `jjwt-jackson`: Librerías para manejar JWT.

### Utilidades y Otros

- **Apache Commons Lang**
  - `commons-lang3`: Utilidades adicionales para trabajar con el lenguaje Java.
- **Lombok**

  - `lombok`: Herramienta para reducir el código boilerplate (opcional).

- **Java Faker**
  - `javafaker`: Generador de datos falsos para pruebas, versión 1.0.2.

### Validación

- **Hibernate Validator**
  - `hibernate-validator`: Implementación de la especificación de Bean Validation, versión 6.2.0.Final.
  - `jakarta.el`: Dependencia necesaria para `hibernate-validator`, versión 3.0.3.

### Documentación de la API

- **SpringDoc OpenAPI**

  - `springdoc-openapi-starter-webmvc-ui`: Para generar la documentación de la API utilizando OpenAPI, versión 2.5.0.

- **Springfox Swagger**
  - `springfox-swagger-ui`: Interfaz de usuario de Swagger para la documentación de la API, versión 3.0.0.

### Dependencias de Pruebas

- **Spring Boot Test**

  - `spring-boot-starter-test`: Herramientas y dependencias para pruebas de Spring Boot.
  - `spring-security-test`: Herramientas para pruebas de seguridad en Spring.

- **Podam**
  - `podam`: Generador de datos de prueba, versión 8.0.1.RELEASE.

### Plugins

- **Spring Boot Maven Plugin**

  - `spring-boot-maven-plugin`: Plugin para empaquetar la aplicación Spring Boot.

- ## **Estrutura de paquetes**

```
 FitConnectBackendApplication.java
📁 config
    📄 DataInitializer.java
    📁 cors
        📄 CorsConfig.java
    📁 security
        📄 JwtAuthenticationFilter.java
        📄 PasswordEncoderConfig.java
        📄 SecurityFilterChainConfig.java
📁 controller
    📁 activity
        📄 ActivityController.java
    📁 auth
        📄 AuthenticationController.java
    📁 comment
        📄 CommentController.java
    📁 notification
        📄 NotificationController.java
    📁 user
        📄 AdminController.java
        📄 UserController.java
📁 dto
    📁 entities
        📄 ActivityDTO.java
        📄 CommentDTO.java
        📄 NotificationDTO.java
        📄 UserDTO.java
    📁 requets
        📄 SignUp.java
        📄 Signin.java
    📁 response
        📄 ErrorDetailsDTO.java
        📄 JwtAuthenticationDTO.java
📁 enums
    📄 Role.java
📁 error
    📄 GlobalExceptionHandler.java
    📁 exception
        📁 activity
            📄 ActivityNotFoundException.java
        📁 notifications
            📄 NotificationCreationException.java
            📄 NotificationNotFoundException.java
        📁 user
            📄 UserNotFoundException.java
📁 persitence
    📁 model
        📄 Activity.java
        📄 Comment.java
        📄 Notification.java
        📄 User.java
    📁 repository
        📄 ActivityRepository.java
        📄 CommentRepository.java
        📄 NotificationRepository.java
        📄 UserRepository.java
📁 service
    📁 implementations
        📁 entity
            📄 ActivityServiceImpl.java
            📄 CommentServiceImpl.java
            📄 NotificationServiceImpl.java
            📄 ProcessingResponseImpl.java
            📄 UserServiceImpl.java
        📁 security
            📄 AuthenticationServiceImpl.java
            📄 JwtServiceImpl.java
    📁 interfaces
        📁 entity
            📄 ActivityServiceI.java
            📄 CommentServiceI.java
            📄 NotificationServiceI.java
            📄 ProcessingResponseI.java
            📄 UserServiceI.java
        📁 security
            📄 AuthenticationServiceI.java
            📄 JwtServiceI.java
📁 utils
    📄 Constants.java
    📁 mappers
        📄 ActivityMapper.java
        📄 CommentMapper.java
        📄 NotificationMapper.java
        📄 UserMapper.java
```

- ## **Descripción**

La estructura de paquetes de la aplicación `fitConnect-backend` está organizada de la siguiente manera:

- **config**: Contiene configuraciones de seguridad y CORS.
- **controller**: Controladores REST para gestionar las diferentes entidades como actividad, autenticación, comentarios, notificaciones y usuarios.
- **dto**: Objetos de transferencia de datos (DTO) para entidades, solicitudes y respuestas.
- **enums**: Enumeraciones utilizadas en la aplicación.
- **error**: Manejadores globales de excepciones y clases de excepciones específicas.
- **persistence**: Modelos y repositorios para la persistencia de datos.
- **service**: Implementaciones e interfaces de servicios para la lógica de negocio, incluyendo seguridad y manejo de entidades.
- **utils**: Clases de utilidades y mapeadores para convertir entre entidades y DTOs.

## Enlace a la documentación de la APIREST:

[FitConnect - APIREST docs.](https://documenter.getpostman.com/view/34870994/2sA3JNa15u)
https://www.youtube.com/watch?v=ZtF4CycqUDg

```

```
