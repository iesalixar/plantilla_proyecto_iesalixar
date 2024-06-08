En este repositorio centralizaremos toda la información relativa a los **Proyectos de Desarrollo de Aplicaciones Web del I.E.S Alixar**.
Al continuación encontraremos los **apellidos y nombre** del alumno/a junto al **título de su proyecto**. El enlace nos dará acceso al repositorio del proyecto (no a la página GitHub del usuario).

En este repositorio se debe incluir la documentación especificada en [Requerimientos y criterios a seguir en el desarrollo de los proyectos](https://github.com/iesalixar/plantilla_proyecto_iesalixar/wiki/a.---Criterios-comunes-para-todos-los-proyectos), así como las indicaciones que el tutor haya podido ir a realizando a lo largo del desarrollo del mismo.

El desarrollo de toda Aplicación Web requiere seguir un [proceso estructurado](https://github.com/iesalixar/plantilla_proyecto_iesalixar/wiki/w1.--PROCESO-ESTRUCTURADO-PARA-DESARROLLO-DE-APLICACIONES-WEB), este de contenido de la wiki te puede ayudar.

---

**Los párrafos anteriores son informativos y no deben aparecer en el reposotirio de los alumnos.**

---

# FitConnet

#### Curso Escolar 2023-2024

#### Autor : [Daniel Perez Serrano.](https://github.com/Dani-Ps)

#### Tutor : [Antonio Gabriel González Casado.](https://github.com/antonio-gabriel-gonzalez-casado)

#### Fecha de Inicio: 15-03-2024.

#### Fecha de Finalización: 12-06-2024.

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

# Estructura del Proyecto

## - **Carpeta src-api**

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

  **Java Faker**

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

## **Estrutura de paquetes**

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

### **Descripción**

La estructura de paquetes de la aplicación `fitConnect-backend` está organizada de la siguiente manera:

- **config**: Contiene configuraciones de seguridad y CORS.
- **controller**: Controladores REST para gestionar las diferentes entidades como actividad, autenticación, comentarios, notificaciones y usuarios.
- **dto**: Objetos de transferencia de datos (DTO) para entidades, solicitudes y respuestas.
- **enums**: Enumeraciones utilizadas en la aplicación.
- **error**: Manejadores globales de excepciones y clases de excepciones específicas.
- **persistence**: Modelos y repositorios para la persistencia de datos.
- **service**: Implementaciones e interfaces de servicios para la lógica de negocio, incluyendo seguridad y manejo de entidades.
- **utils**: Clases de utilidades y mapeadores para convertir entre entidades y DTOs.

## - **Carpeta src-frontend**

Esta carpteta contiene el código de la React App de Fitconnet, aqui se detalla el stack tecnológico usado:

## Stack Tecnológico del Proyecto

El proyecto utiliza las siguientes tecnologías y dependencias:

### Frameworks y Librerías Principales

- **React 18.3.1**: Biblioteca principal para construir interfaces de usuario.
- **ReactDOM 18.3.1**: Paquete para manipulación del DOM en React.

### Librerías de Estilo y Animación

- **@fontsource/roboto 5.0.13**: Fuente tipográfica Roboto.
- **framer-motion 11.2.10**: Librería para animaciones.
- **sass 1.77.1**: Preprocesador CSS.

### Manejo de Rutas

- **react-router-dom 6.23.1**: Librería para manejar la navegación y las rutas en React.

### Manejo de Estado y Propiedades

- **prop-types 15.8.1**: Librería para validación de tipos de propiedades en componentes de React.

### Solicitudes HTTP

- **axios 1.7.2**: Cliente HTTP para realizar solicitudes a servidores.
- **react-axios 2.0.6**: Integración de Axios en componentes de React.

### Componentes y Utilidades Adicionales

- **react-md 5.1.6**: Componentes Material Design para React.
- **react-modal 3.16.1**: Componente para crear modales accesibles.
- **react-tooltip 5.26.4**: Componente para mostrar tooltips.

### Testing

- **@testing-library/react 13.4.0**: Utilidades para probar componentes de React.
- **@testing-library/jest-dom 5.17.0**: Extensiones de matchers de Jest para trabajar con el DOM.
- **@testing-library/user-event 13.5.0**: Simula eventos de usuario para pruebas.
- **jest**: Framework de pruebas utilizado por Create React App.

### Herramientas de Desarrollo

- **react-scripts 5.0.1**: Scripts y configuración para Create React App.
- **@babel/plugin-transform-private-property-in-object 7.24.6**: Plugin de Babel para transformar propiedades privadas en objetos.

### Monitoreo de Rendimiento

- **web-vitals 2.1.4**: Biblioteca para medir métricas de rendimiento web.

### Scripts de NPM

- `start`: Inicia la aplicación en modo de desarrollo.
- `build`: Construye la aplicación para producción.
- `test`: Ejecuta las pruebas.
- `eject`: Expone la configuración de Create React App para personalización.

### Configuración de ESLint

- **react-app**: Extiende la configuración por defecto de Create React App.
- **react-app/jest**: Extiende la configuración de ESLint para Jest.

### Configuración de Browserslist

- **production**: Define los navegadores soportados en producción.
- **development**: Define los navegadores soportados en desarrollo.

Este stack tecnológico proporciona una base sólida para desarrollar, estilizar, probar y desplegar una aplicación React moderna con una variedad de herramientas para mejorar la experiencia de desarrollo y el rendimiento de la aplicación.

## **Estrutura de carpetas**

```
📄 App.js
📄 App.test.js
📁 _css
    📄 app.css
    📄 app.css.map
📁 assest
    📁 examples
        📄 avatar.png
        📄 pexels-anush-gorak-1431282.jpg
        📄 pexels-heart-rules-711187.jpg
        📄 pexels-martine-savard-34669.jpg
        📄 pexels-pixabay-159515.jpg
        📄 pexels-pixabay-358042.jpg
        📄 pexels-pixabay-54326.jpg
        📄 portada.jpg
    📁 icon
        📄 logo-clear.jsx
        📄 logo-dark.jsx
        📄 sidebarIcons-clear.jsx
        📄 sidebarIcons-dark.jsx
        📄 style.scss
        📄 themeIcons.jsx
📁 components
    📁 common
        📁 activity
            📄 activityPost.jsx
            📁 components
                📁 icon
                    📄 activityIcon-clear.jsx
                    📄 activityIcon-dark.jsx
                📁 profilePicture
                    📄 profilePicture.jsx
                    📄 style.scss
            📄 style.scss
        📁 addActivity
            📄 addActivityCard.jsx
            📁 components
                📁 icons
                    📄 addImageIcon.jsx
                    📄 closeIcon.jsx
                    📄 style.scss
                📁 timeInput
                    📄 style.scss
                    📄 timeInput.jsx
            📄 style.scss
        📁 imageUpload
            📄 imageUpload.jsx
    📁 layout
        📁 footer
            📄 footer.jsx
            📄 style.scss
        📁 header
            📁 components
                📁 icon
            📄 header.jsx
            📄 style.scss
        📁 navbar
            📁 components
                📁 buttons
                    📄 buttons.jsx
                    📄 style.scss
                📁 profilePicture
                    📄 profilePicture.jsx
                    📄 style.scss
            📄 navbar.jsx
            📄 style.scss
        📁 skeleton
            📄 skeleton.jsx
📁 contexts
    📄 AuthProvider.js
    📄 ModalProvider.js
    📄 ScreenProvider.js
    📄 ThemeProvider.js
📁 hooks
    📄 useAuth.js
    📄 useContinuousUpdates.js
    📄 useModal.js
    📄 useScreen.js
    📄 useTheme.js
📄 index.js
📁 model
    📄 ActivityTypes.js
    📄 activityDTO.js
    📄 userDTO.js
📁 pages
    📁 auth
        📄 authPage.jsx
        📁 components
            📁 buttons
                📄 buttons.jsx
                📄 style.scss
            📁 form
                📄 login.jsx
                📄 signup.jsx
                📄 style.scss
    📁 home
        📁 components
            📁 buttons
                📄 buttons.jsx
                📄 style.scss
        📄 homePage.jsx
📄 reportWebVitals.js
📁 scss
    📄 _clear-theme.scss
    📄 _dark-theme.scss
    📄 _imports.scss
📁 service
    📄 activityService.js
    📄 adminService.js
    📄 authService.js
    📄 imageService.js
    📄 notificationService.js
    📄 routerProtectionService.js
    📄 userService.js
📄 setupTests.js

```

### Descripción de la Estructura

#### Archivos Raíz

- `App.js`: El componente principal de la aplicación React.
- `App.test.js`: Pruebas unitarias para el componente `App`.

#### Carpetas Principales

- **\_css**: Contiene archivos CSS para el estilado de la aplicación.

  - `app.css`: Hoja de estilos principal.
  - `app.css.map`: Mapa de origen para el archivo CSS.

- **assest**: Contiene recursos estáticos como imágenes y iconos.

  - **examples**: Ejemplos de imágenes usadas en la aplicación.
  - **icon**: Componentes de React que representan iconos, junto con sus estilos.

- **components**: Contiene componentes reutilizables de la aplicación.

  - **common**: Componentes comunes utilizados en diferentes partes de la aplicación.
    - **activity**: Componentes relacionados con actividades, incluyendo iconos y estilos.
    - **addActivity**: Componentes para añadir actividades, incluyendo iconos y estilos.
    - **imageUpload**: Componente para la subida de imágenes.
  - **layout**: Componentes de diseño de la aplicación.
    - **footer**: Pie de página.
    - **header**: Encabezado de la aplicación.
    - **navbar**: Barra de navegación.
    - **skeleton**: Componente para mostrar esqueletos de carga.

- **contexts**: Proveedores de contexto para manejar estados globales de la aplicación.

  - `AuthProvider.js`: Proveedor de autenticación.
  - `ModalProvider.js`: Proveedor de modales.
  - `ScreenProvider.js`: Proveedor de estados de pantalla.
  - `ThemeProvider.js`: Proveedor de temas.

- **hooks**: Hooks personalizados para manejar lógica específica.

  - `useAuth.js`: Hook para la autenticación.
  - `useContinuousUpdates.js`: Hook para actualizaciones continuas.
  - `useModal.js`: Hook para manejar modales.
  - `useScreen.js`: Hook para manejar estados de pantalla.
  - `useTheme.js`: Hook para manejar temas.

- **model**: Definiciones de modelos de datos.

  - `ActivityTypes.js`: Tipos de actividad.
  - `activityDTO.js`: DTO para actividad.
  - `userDTO.js`: DTO para usuario.

- **pages**: Componentes de página para diferentes rutas.

  - **auth**: Página de autenticación.
    - **components**: Componentes específicos para la página de autenticación.
  - **home**: Página de inicio.
    - **components**: Componentes específicos para la página de inicio.

- **scss**: Archivos SCSS para el estilado temático.

  - `_clear-theme.scss`: Estilos para el tema claro.
  - `_dark-theme.scss`: Estilos para el tema oscuro.
  - `_imports.scss`: Importaciones SCSS.

- **service**: Servicios para manejar la lógica de negocio y las interacciones con APIs.
  - `activityService.js`: Servicio para actividades.
  - `adminService.js`: Servicio para administración.
  - `authService.js`: Servicio para autenticación.
  - `imageService.js`: Servicio para manejo de imágenes.
  - `notificationService.js`: Servicio para notificaciones.
  - `routerProtectionService.js`: Servicio para protección de rutas.
  - `userService.js`: Servicio para manejo de usuarios.

#### Otros Archivos

- `index.js`: Punto de entrada principal de la aplicación.
- `reportWebVitals.js`: Herramienta para medir el rendimiento de la aplicación.
- `setupTests.js`: Configuración para pruebas unitarias.

## Enlace a la documentación de la APIREST:

[FitConnect - APIREST docs.](https://documenter.getpostman.com/view/34870994/2sA3JNa15u)
https://www.youtube.com/watch?v=ZtF4CycqUDg
