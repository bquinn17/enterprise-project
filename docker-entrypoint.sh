#!/usr/bin/env bash

# Wait for mySQL service.
set +o xtrace
echo "Waiting for MySQL to become available ..."
until mysql -h "erpsales_database" -u admin -p"toor" -e "quit" >> /dev/null 2>&1; do
  echo "MySQL is unavailable. Sleeping."
  sleep 3
done
echo "MySQL is up"

java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=prod -jar target/swagger-spring-1.0.0.jar