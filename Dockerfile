FROM maven:3.3-jdk-8
VOLUME /tmp
COPY pom.xml ./home
COPY src/ /home/src/
WORKDIR /home/
RUN mvn clean install
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.datasource.url=jdbc:mysql://erpsales_database_1:3306/sales?useSSL=false", "-jar","target/swagger-spring-1.0.0.jar"]
