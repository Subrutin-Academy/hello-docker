FROM eclipse-temurin:17.0.5_8-jre-ubi9-minimal
ARG JAR_FILE=target/*.jar

WORKDIR /app

COPY ${JAR_FILE} app.jar

RUN useradd appuser
USER appuser

ENTRYPOINT ["java","-jar","app.jar"]
