FROM openjdk:11
WORKDIR /app
COPY release.jar ./
EXPOSE 80
CMD ["java", "-jar", "/app/release.jar"]