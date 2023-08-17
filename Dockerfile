FROM eclipse-temurin:17-jdk-ubi9-minimal as builder
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .

RUN chmod +x ./mvnw && ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install


FROM eclipse-temurin:17.0.5_8-jre-ubi9-minimal
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY --from=builder /app/${JAR_FILE} /app/app.jar

RUN useradd appuser
USER appuser

ENTRYPOINT ["java", "-jar","app.jar"]
