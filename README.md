## Prerequisites

- Docker/Docker compose
- JDK 18
- Maven (not using the wrapper here, is a TODO to ensure this works)
 
## Build + Run

Build using maven:

    mvn clean install -DskipTests -PdockerBuild

Run using the docker-compose script inside docker dir:

    cd docker
    ./run-docker-compose up --frontend

This will map the host's port 8081 (first part) to the docker container's port 8080 (TODO: deprecate this accessibility)

Also will expose the frontend to the client on port 3000

The API can be accessed by using the following address in the default configuration:

    http://localhost:8081/<path>

Website can be accessed at the following:

    http://localhost:3000

## Shutdown:

Use the included `run-docker-compose`script to shutdown the app:

    cd docker
    ./run-docker-compose down --frontend

## TODO:

- Integration Tests
  - [x] use maven-exec-plugin to trigger docker-compose in the pre-integration-test phase
  - may want a separate docker-compose file to not have persistent storage through docker volumes
    - alternatively, could use testcontainers
- Unit Tests
  - Separate profiles through the application-${PROFILE_NAME}.properties
    - (e.g. would want to use H2 for local testing)
- Separate Dockerfiles for dev/prod
  - Use maven profiles for toggling this, maybe set variable based on profile that sets name of dockerfile
  - Want to enable remote debugging on dev, not prod
- Beef-up the frontend (obviously)


## Notes:

If running locally, may need to start mysql-server. On first run, you may need to create the `test_user` db user and start the mysql service

On Mac with mysql installed using brew, setup may look like this:

    brew services mysql start

    mysql --user=root --execute="CREATE USER 'test_user'@'localhost' IDENTIFIED BY 'test1234'"

    mysql --user=root --execute="GRANT ALL PRIVILEGES ON *.* TO 'test_user'@'localhost' WITH GRANT OPTION"

    mysql --user=root --execute="FLUSH PRIVILEGES"
