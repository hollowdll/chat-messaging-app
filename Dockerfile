## Based on https://community.render.com/t/3232

# Build stage
FROM maven:3.8.6-eclipse-temurin-17-focal AS build
COPY src/main /home/app/src/main
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Package stage
FROM eclipse-temurin:17-jre-focal
COPY --from=build /home/app/target/chat-app-0.0.1-SNAPSHOT.jar /usr/local/lib/pkg.jar
EXPOSE 8080

# Set environment variables
ENV PORT=8080

# Execute
ENTRYPOINT ["java", "-jar", "/usr/local/lib/pkg.jar"]
CMD ["echo", "Application deployed!"]