FROM maven:3.3-jdk-8
VOLUME /tmp
COPY pom.xml ./home
COPY src/ /home/src/
WORKDIR /home/
RUN mvn clean install
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","target/swagger-spring-1.0.0.jar"]
