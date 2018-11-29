FROM maven:3.3-jdk-8
COPY pom.xml /home
COPY src/ /home/src/
WORKDIR /home/
RUN apt-get update && apt-get install -y mysql-client
RUN mvn clean install -DskipTests
EXPOSE 8080
COPY docker-entrypoint.sh /home/
ENTRYPOINT ["/home/docker-entrypoint.sh"]
# CMD ["/bin/bash"]