FROM openjdk:8-jdk-alpine
COPY target/ecommerce-docker.jar  ecommerce-docker.jar  
EXPOSE 8084
ENTRYPOINT ["java" ,"-jar", "/ecommerce-docker.jar"]