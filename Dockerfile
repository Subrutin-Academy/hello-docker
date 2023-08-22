FROM eclipse-temurin:17-jdk-ubi9-minimal as builder
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .

RUN chmod +x ./mvnw && ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean package -Dmaven.test.skip


FROM eclipse-temurin:17.0.5_8-jre-ubi9-minimal as layered
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY --from=builder /app/${JAR_FILE} /app/app.jar
RUN java -Djarmode=layertools -jar app.jar extract



FROM eclipse-temurin:17.0.5_8-jre-ubi9-minimal
ARG JAR_FILE=target/*.jar
WORKDIR /app
COPY --from=layered /app/dependencies/ ./
COPY --from=layered /app/spring-boot-loader/ ./
COPY --from=layered /app/snapshot-dependencies/ ./
COPY --from=layered /app/application/ ./

ENV DATABASE_ADDR=database
ENV DATABASE_NAME=book_catalog
ENV DATABASE_USER=subrutin
ENV DATABASE_PASSWORD=subrutin

RUN useradd appuser
USER appuser

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
