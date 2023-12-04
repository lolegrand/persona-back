FROM openjdk:11
WORKDIR /app
COPY release.jar ./
EXPOSE 8080
CMD ["java", "-jar", "/app/release.jar"]