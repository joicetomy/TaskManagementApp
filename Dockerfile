FROM openjdk:11-jdk
ADD target/Task_Management_App-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/app.jar"]