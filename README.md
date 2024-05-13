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

En este apartado el alumno explicará el contenido del repositorio y de todas las carpetas relevantes del mismo. Para facilitar la gestión de la entrega, todo el código y documentación debe estar en este repositorio.

Por lo anterior, un proyecto que contenga un Frontend en una tecnología o framework (por ejemplo Angular) y una API REST en otra tecnología o framework (Springboot, Express) deberá tener la siguiente estructura de directorios en el repositorio de entrega:

- src-api
- src-frontend
- docs
- README.md

En el caso anterior, si se quiere desplegar de forma automatizada a partir del control de versiones, lo habitual es que estén los dos proyectos en repositorios separados. Por lo que se deberá configurar el despliegue automático para indicarle la raíz del código fuente de cada proyecto (si es posible) o hacer dos folks del repositorio principal uno para la API y otro para el frontend y adaptar los directorios para poder realizar el despliegue automático.

En un proyecto monolítico (tecnología servidor: Springboot, Django, Express, PHP,... con un sistema de templates propio para el frontend: Thymeleaf, jinja, ejs,...) deberá tener la siguiente estructura en el repositorio de entrega:

- src
- docs
- README.md

## Enlace a la documentación de la APIREST:

[FitConnect - APIREST docs.](https://documenter.getpostman.com/view/34870994/2sA3JNa15u)
