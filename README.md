## Prerequisites

- Docker/Docker compose
- JDK 18
- Maven (not using the wrapper here, is a TODO to ensure this works)
 
## Docker Setup

Create docker volumes:

    docker volume create mysql_data

    docker volume create mysql_config

Create docker network for db/app communication:

    docker network create mysqlnet

## Build + Run

Build using maven:

    mvn clean install -DskipTests

Run using docker-compose from the root dir:

    docker compose -f app-main/docker-compose.dev.yml up

This will map the host's port 8081 (first part) to the docker container's port 8080

- Naturally, the user may choose the host port to which the API will be mapped in the docker-compose ports section

The API can be accessed by using the following address in the default configuration:

    http://localhost:8081/<path>

## TODO:

- Integration Tests
  - use maven-exec-plugin to trigger docker-compose in the pre-integration-test phase
  - may want a separate docker-compose file to not have persistent storage through docker volumes
    - alternatively, could use testcontainers
- Unit Tests
  - Separate profiles through the application-${PROFILE_NAME}.properties
    - (e.g. would want to use H2 for local testing)


## Notes:

If running locally, may need to start mysql-server. On first run, you may need to create the `test_user` db user and start the mysql service

On Mac with mysql installed using brew, setup may look like this:

    brew services mysql start

    mysql --user=root --execute="CREATE USER 'test_user'@'localhost' IDENTIFIED BY 'test1234'"

    mysql --user=root --execute="GRANT ALL PRIVILEGES ON *.* TO 'test_user'@'localhost' WITH GRANT OPTION"

    mysql --user=root --execute="FLUSH PRIVILEGES"
