FROM openjdk:8
ADD build/libs/myfirstdemo-0.0.1-SNAPSHOT.jar myfirstdemo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/myfirstdemo-0.0.1-SNAPSHOT.jar"]