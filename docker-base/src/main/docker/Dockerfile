FROM eclipse-temurin:18
LABEL authors="randy"

#ENTRYPOINT ["/bin/bash"]

# Create a separate base image to use, move these to that
# do this by having a submodule just for the docker base image
# that will install mysql-server, setup the login info, etc...
RUN apt update
RUN apt install -y mysql-server
# end comment

EXPOSE 8080
