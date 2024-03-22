# Taller 7:  Aplicación distribuida segura en todos sus frentes

Se desarrollo una aplicación Web segura que permitir un acceso seguro desde el browser a la aplicación y entre servicios. Es decir, garantiza autenticación, autorización e integridad de usuarios y servicios.

Esta aplicación web fue creada utilizando el micro-framework de Spark java (http://sparkjava.com/). La cual se desplego en AWS utilizando EC2 y Docker

## Prerequisitos
Los prerrequisitos tecnológicos que necesita este taller son:

* Maven

* Git

* Java 17

* Docker

* Docker compose

* Docker hub

* AWS

## Instalación y Ejecución

1. Se clona el repositorio:
```
git clone https://github.com/AREP-2024/Taller7.git
```
2. Ingresamos a la carpeta del repositorio que clonamos anteriormente:
```
cd Taller7
```

Hay dos formas de realizar la instalación y ejecución:

**a. Trayendo las imagenes de DockerHub**
1. Ejecutamos el docker-compose:
```
docker-compose up -d
```
2. Verificamos que los contenedores se estén ejecutando:
```
docker ps
```
![](imagenes/docker_ps.png)
También se puede verificar que los contenedores se estén ejecutando en Docker Desktop.
![](imagenes/dockerD_local.png)

3. Probamos nuestra aplicación accediendo a la siguiente URL en el navegador
```
https://localhost:8080/login.html
```
![](imagenes/Browser_local.png)

**b. Construyendo las imagenes con el archivo docker-compose.build**
1. Nos movemos a la carpeta secure-login:
```
cd secure-login
```
2. Construimos el proyecto:
```
mvn package
```
3. Nos movemos nuevamente a la carpeta raíz:
```
cd ..
```
4. Nos movemos a la carpeta secure-server:
```
cd secure-server
```
5. Construimos el proyecto:
```
mvn package
```
6. Nos movemos nuevamente a la carpeta raiz:
```
cd ..
```
7. Ejecutamos el docker-compose.build:
```
docker-compose -f docker-compose.build.yml up -d
```
8. Verificamos que los contenedores se esten ejecutando
```
docker ps
```
![](imagenes/docker_ps.png)
También se puede verificar que los contenedores se estén ejecutando en Docker Desktop.
![](imagenes/dockerD_local.png)

9. Probamos nuestra aplicación accediendo a la siguiente URL en el navegador:
```
https://localhost:8080/login.html
```
![](imagenes/Browser_local.png)

## ¿Cómo fuciona la aplicación?

1.Antes de utilizar nuestra aplicación, es necesario crear un contenedor Docker basado en Ubuntu que permita agregar usuarios a nuestra base de datos MongoDB. Para lograr esto, ejecutamos el siguiente comando:
 
```
docker run -d --name ubuntu ubuntu tail -f /dev/null
```
 
2.Luego vamos a listar las redes Docker para ver el nombre de la red que estamos usando para este proyecto y así mismo conectarnos a esta, para esto ejecutamos el comando:
 
```
docker network ls
```
![](imagenes/dockerNetwork.png)

**Nota:** Como podemos observar en la imagen nuestra red se llama **taller7_reddocker**
 
3.Ahora para conectar nuestro contenedor Ubuntu a la red, ejecutamos el comando:
 
```
docker network connect taller7_reddocker ubuntu
```
4.Luego procederemos a instalar curl para poder agregar usuarios, siguiendo estos pasos:
 
a.  Nos conectamos al contenedor Ubuntu:
```
docker exec -it ubuntu bash
```
 
b.  Actualizamos la lista de paquetes:
```
apt update
```
 
c.  Instalamos curl:
```
apt install curl
```

5.Una vez instalado curl, vamos a ejecutar el comando para agregar un usuario de la siguiente manera:
```
curl --location 'http://server:10000/user' \
--header 'Content-Type: application/json' \
--data '{
    "usuario": "Eduard",
    "contrasena": "0825"
}'
```
**Nota:** El curl anterior se hizo con ayuda de postman.
![](imagenes/posman.png)

En la siguiente imagen, podemos ver el resultado en Docker Dekstop al ejecutar el curl:
![](imagenes/Curl_dockerD.png)
 
**Nota:** Si el usuario se creó correctamente se mostrará un mensaje de Usuario creado como se puede observar en la imagen anterior, de lo contrario se envía el mensaje de Usuario no creado.
 
6.Ahora vamos a nuestro browser en la dirección url indicada anteriormente, para probar la aplicación.
 
```
https://localhost:8080/login.html
```

Allí vamos a acceder con las credenciales del usuario que agregamos anteriormente, para esto ingresamos en el campo de Usuario Eduard y en el de la Contraseña 0825 y damos click en iniciar sesión.
 
Si las credenciales ingresadas son correctas se mostrará un mensaje de bienvenida como se puede observar en la imagen:
 
![](imagenes/Browser_B.png)

Si por el contrario no son correctas, se mostrará un mensaje de usuario o contraseña incorrectos como se visualiza en la siguiente imagen:
 
![](imagenes/Browser_x.png)
 
 
7.También podemos observar los usuarios que han sido agregados a nuestra base de datos, para esto
a.  Ejecutamos el siguiente comando en el contenedor mongodb en docker desktop en la opción de exec para conectarnos a la base de datos
 
```
mongosh mongodb://LuisaGiron:LuisaGiron123@mongodb:27017/
```
![](imagenes/Conexion_mongo.png)
 
b.  Luego vamos a acceder a la base de datos con el nombre, utilizando el siguiente comando:
```
use taller7
```
 
c.  Ahora vamos a acceder a la tabla donde tenemos la información de los usuarios, para esto ejecutamos el siguiente comando:
```
db.usuarios.find()
 ```
Como podemos observar en la siguiente imagen tenemos dos usuarios agregados con su respectivo username y contraseña encriptada.
 
![](imagenes/DockerDB.png)
 

### Ejecución de Test
 
1.Desde la raiz nos movemos a la carpeta secure-login
 
```
cd secure-login
```
 
2.Ejecutamos las pruebas con el siguiente comando
 
```
mvn test
```
 
3.Nos devolvemos nuevamente a la carpeta raiz
 
```
cd ..
```
 
4.Ahora nos movemos a la carpeta secure-server
 
```
cd secure-server
```
 
5.Ejecutamos las pruebas con el siguiente comando
 
```
mvn test
```
## Despliegue en AWS

En el siguiente enlace usted podrá ver el video de como desplegar en AWS:


## Arquitectura del programa

Esta arquitectura consta de dos proyectos secure-login y secure-server los cuales proporcionar una autenticación segura a los usuarios y una gestión confiable de su información.

Para esta arquitectura se crearon estos dos proyectos, debido a que se esta trabajando con microservicios llevando a que sea más fácil conectar multiples instancias de manera más eficiente y escalable.
 
**Componentes**
* **secure-login:** Se encarga de manejar las solicitudes de autenticación de usuarios. Utiliza un servicio de autenticación para validar las credenciales de inicio de sesión proporcionadas por los usuarios.
 
* **secure-server:** actúa como servidor principal para recibir las solicitudes de autenticación y guardar la información de los usuarios de manera segura en una base de datos MongoDB.
 
* **MongoDB:** Base de datos NoSQL utilizada para almacenar los usurios con sus credenciales.

![](imagenes/arquitectura.png)
**Estructura Proyecto secure-login:**
 
El proyecto está estructurado en diferentes paquetes y clases:
 
**App:** Clase principal que configura y ejecuta la aplicación Secure-Login. Inicializa el controlador Spark y establece la configuración necesaria para el funcionamiento del sistema.
 
**1. integracion**
* **ClienteHttp:** Cliente HTTP utilizado para realizar llamadas a un servicio de autenticación. Utiliza OkHttp para realizar las solicitudes HTTP.
 
**2. controlador**
* **Controlador:** Interfaz que define los métodos para los controladores.
 
* **SparkControlador:** Implementación de un controlador Spark para manejar las solicitudes de autenticación.Utiliza un servicio de autenticación para validar las credenciales de inicio de sesión.
 
**3. servicio**
* **ServicioAutenticacion:** Servicio encargado de autenticar a los usuarios utilizando un servicio interno y gestionar las solicitudes de autenticación.

* **TransformacionDatos:** Clase para transformar datos en otro formato, en este caso transformamos un string de información de inicio de sesión en un objeto Login.

**4. domain**
* **Login:** Clase que representa los datos de inicio de sesión de un usuario.

 
 
**Estructura Proyecto secure-server:**
 
El proyecto está estructurado en diferentes paquetes y clases:
 
**App:** Clase principal que configura y ejecuta la aplicación Secure-Server. Inicializa los controladores y establece la configuración necesaria para el funcionamiento del sistema, como la conexión a la base de datos y la configuración de seguridad.
 
**1. controlador**
* **Controlador:** Interfaz que define los métodos para los controladores.
 
* **SparkControlador:** Implementación de un controlador Spark para manejar las solicitudes de autenticación y de creación de usuarios. Utiliza servicios para autenticar usuarios y guardar la información de los nuevos usuarios.
 
**2. persistencia**
* **CRUD:** Interfaz para operaciones CRUD como guardar, eliminar, buscar y buscar todos.
 
* **LoginDAO:** Implementación de la interfaz CRUD para manejar la persistencia de los datos de inicio de sesión de los usuarios en MongoDB.
 
* **MongoUtil:** Clase para realizar la conexión a la base de datos MongoDB.
 
**3. servicio**
* **ServicioAutenticacion:** Servicio encargado de autenticar a los usuarios utilizando la base de datos y gestionar las solicitudes de autenticación.

* **ServicioEncriptacion:** Servicio que proporciona funcionalidades de encriptación utilizando el algoritmo SHA-256 para generar un hash para el password.

* **ServicioGuardar:** Servicio encargado de guardar la información de nuevos usuarios en la base de datos.

**4. domain**
* **Login:** Clase que representa los datos de inicio de sesión de un usuario.

## Construido con

* [Maven](https://maven.apache.org/): Maven es un herramienta que proporciona una estructura estándar, gestión de dependencias y automatización de tareas en los proyectos software desarrollados en Java.

* [Git](https://platzi.com/blog/que-es-github-como-funciona/): Es una plataforma de desarrollo colaborativo basado en Git que utiliza un sistema de versiones distribuida para el seguimiento del código fuente durante el desarrollo.

* [Java 17](https://www.java.com/es/download/help/java8_es.html): Es un lenguaje de programación de propósito general, orientado a objetos y diseñado para ser independiente de la plataforma.

* [Spark](https://sparkjava.com/):Spark es un framework de Java que permite desarrollar aplicaciones web. Se caracteriza por ser super sencillo y muy ligero.

* [Docker](https://www.docker.com/): Es una plataforma de código abierto diseñada para facilitar la creación, implementación y ejecución de aplicaciones en contenedores.

* [Docker Hub](https://hub.docker.com/): Es un servicio en la nube proporcionado por Docker que actúa como un repositorio centralizado para imágenes Docker. Es un lugar donde los desarrolladores pueden almacenar, distribuir y compartir imágenes Docker públicas y privadas, así como colaborar en proyectos relacionados con Docker.

* [Docker Desktop](https://www.docker.com/products/docker-desktop/): Es una aplicación de escritorio que proporciona una experiencia unificada para desarrollar, implementar y administrar aplicaciones en contenedores Docker en sistemas operativos Windows y macOS.

* [Visual Studio Code](https://code.visualstudio.com/):Es un entorno de desarrollo integrado (IDE) ligero y de código abierto desarrollado por Microsoft.

* [HTML](https://developer.mozilla.org/es/docs/Web/HTML): Es el lenguaje de marcado que se utiliza para crear páginas web. Se define como un conjunto de etiquetas que se utilizan para estructurar y dar significado al contenido de una página web.

* [JavaScript](https://aws.amazon.com/es/what-is/javascript/): Es un lenguaje de programación de alto nivel, interpretado y orientado a objetos. Se utiliza principalmente en el desarrollo web para crear páginas web interactivas y dinámicas.

* [AWS](https://aws.amazon.com/es/):  (Amazon Web Services) es una plataforma de servicios de computación en la nube ofrecida por Amazon.com. Que proporciona una amplia gama de servicios, incluidos almacenamiento, computación, bases de datos, análisis, aprendizaje automático, inteligencia artificial, Internet de las cosas (IoT), seguridad, desarrollo de aplicaciones, redes y mucho más.

* [Mongo DB](https://www.mongodb.com/es): MongoDB es un sistema de gestión de bases de datos NoSQL (no solo SQL) de código abierto, orientado a documentos.

## Autor
[Luisa Fernanda Bermudez Giron](https://www.linkedin.com/in/luisa-fernanda-bermudez-giron-b84001262/) - [Luisa Fernanda Bermudez Giron](https://github.com/LuisaGiron)

## Licencia 
**©** Luisa Fernanda Bermudez Giron, Estudiante de Ingeniería de Sistemas de la Escuela Colombiana de Ingeniería Julio Garavito.