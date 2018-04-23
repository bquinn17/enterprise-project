FROM maven:3.3-jdk-8
COPY pom.xml /home
COPY src/ /home/src/
WORKDIR /home/
RUN mvn clean install -DskipTests
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=prod", "-jar","target/swagger-spring-1.0.0.jar"]
