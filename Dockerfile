FROM openjdk:21-jdk

COPY target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]