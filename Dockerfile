FROM openjdk:17-jdk-slim

LABEL org.opencontainers.image.authors="jmantillap@gmail.com"
#host.docker.internal --> maquina fisica donde esta la base de data. host anfitrion
ENV JDBC_URL_BD=jdbc:mysql://host.docker.internal:3306/franquicia
ENV USERNAME_BD=root
ENV PASSWORD_BD=root

COPY target/demo-franquicia-1.0.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]


#$ docker build -t jmantillap/franquicia-app:v1 .
#$ docker run -it --name franquicia-contenedor-app -p 8080:8080 <identificadorImagen>

#creacion del contenedor y que lo elimine cuando se detenga el contenedor
#$ docker run -it --rm --name franquicia-contenedor-app -p 8080:8080 <identificadorImagen>
