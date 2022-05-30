# TareasAppServer

Este proyecto fue generado con java 11.

# Pre requisitos java 11, MySQL

## Correr el servicio localmente

* Configure el archivo `application.properties` con las credenciales de usuario y clave de `MySQL`
* Genere nuevamente el `bootJar` y ejecutelo con java -jar build/libs/tareasApp-1.0.jar

NOTA: cree la base de datos `tareasdb` y deje la propiedad en `spring.jpa.hibernate.ddl-auto = create` para insertar los datos de forma automáticamente

## Api documentation
https://documenter.getpostman.com/view/12994430/UVRDFk8Y

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/12994430-54b43364-5735-4402-a850-e5997c0a9f5c?action=collection%2Ffork&collection-url=entityId%3D12994430-54b43364-5735-4402-a850-e5997c0a9f5c%26entityType%3Dcollection%26workspaceId%3D4b12f55d-e4bc-43b5-bfed-e06650379863)