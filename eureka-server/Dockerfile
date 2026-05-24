#Use java 21
FROM eclipse-temurin:21-jdk-alpine

#Setting working directory inside the container of the Docker
WORKDIR /app

#Copy the jar file 
COPY target/eureka-server-0.0.1-SNAPSHOT.jar app.jar

#Tells about the entry point and the jar file
ENTRYPOINT ["java","-jar","app.jar"]