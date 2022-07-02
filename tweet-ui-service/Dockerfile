FROM --platform=linux/amd64 openjdk:11
ADD target/tweet-ui-service-0.0.1-SNAPSHOT.jar tweet-ui-service-0.0.1-SNAPSHOT.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar","tweet-ui-service-0.0.1-SNAPSHOT.jar"]