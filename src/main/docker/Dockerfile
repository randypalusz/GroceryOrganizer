FROM eclipse-temurin:18
LABEL authors="randy"

ENTRYPOINT ["java", "-jar", "/maven/${name}.jar"]

ARG name
ARG version

ADD target/${name}-${version}.jar /maven/${name}.jar

# Create a separate base image to use, move these to that
RUN apt update
RUN apt install -y mysql-server
# end comment

EXPOSE 8080