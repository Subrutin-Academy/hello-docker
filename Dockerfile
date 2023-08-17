FROM eclipse-temurin:17.0.5_8-jre-ubi9-minimal AS layered
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN java -Djarmode=layertools -jar app.jar extract


FROM eclipse-temurin:17.0.5_8-jre-ubi9-minimal
ARG JAR_FILE=target/*.jar

WORKDIR /app

COPY --from=layered /app/dependencies/ ./
COPY --from=layered /app/spring-boot-loader/ ./
COPY --from=layered /app/snapshot-dependencies/ ./
COPY --from=layered /app/application/ ./

RUN useradd appuser
USER appuser

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
