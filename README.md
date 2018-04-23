# TERM-PROJECT: KennUWare

An ERP system developed in Java 8 using Spring Boot

## Silo:  ##
team_sales


## Team ##

- Bryan Quinn - bquinn17
- Peter Howard - peterhoward45
- Aaron Stadler - aaron13michael
- Kevin Barnett - pdfkpb
- Brendan Jones - BrendanJones44

## Docker Instructions ##

- Prerequisite: have Docker installed.
- At the root level of the project repo run: `docker-compose build`
- Then run `docker-compose up`

## Live URL ##
- http://167.99.239.47

## API Documentation ##
- http://167.99.239.47:8080/swagger-ui.html

## Prerequisites ##

- Java 8 (openjdk and openjre 1.8)

- Maven3 (3.0.5 or higher) https://gist.github.com/stephanetimmermans/8fa47ddfe1afede6e310

- MySQL (mysql-client and mysql-server)

- Yarn (may need to use PATH="$PATH:/usr/local/bin" yarn install)

- IntelliJ - as developer platform


## Setup ##

### Database ###

https://wiki.gandi.net/en/hosting/using-linux/tutorials/ubuntu/createdatabase

1. Create a database called sales

2. Grant a user all permissions to that databse to -u admin -p toor

### Front end ###

 - `cd src/react-src/`

 - `yarn install`

 - `yarn start`

### Back end ###

1. `git clone https://github.com/RIT-SWEN-343-201705-KennUWare/erp-2175-erp-sales.git`

2. `mvn install`

3. `java -jar target/swagger-spring-1.0.0.jar`

4. Go to `http://127.0.0.1:8080`

## Information for testing ##

1. The admin that can login to the employee page has a username: salesRepManager and a password:password

## License ##
MIT License

See LICENSE for details.
