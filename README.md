run using the following after build:

    docker run -p 8080:8080 randypalusz/grocery-organizer:latest

This will map the host's port 8080 (first part) to the docker container's port 8080

- Naturally, the user may choose the host port to which the API will be mapped

The API can be accessed by using the following address in the default configuration:

    http://localhost:8080/<path>