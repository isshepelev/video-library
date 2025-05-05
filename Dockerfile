FROM maven:3.9.9-amazoncorretto-17-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean verify

FROM openjdk:17-oracle
ENV JAVA_OPTS="-Xmx256m"
COPY --from=build /app/target/video-cd-library-0.0.1.jar app.jar
EXPOSE 8080
CMD ["sh", "-c", "exec java -Djava.security.egd=file:/dev/./urandom $JAVA_OPTS -jar app.jar"]