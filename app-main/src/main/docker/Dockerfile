FROM randypalusz/grocery-organizer-base:latest
LABEL authors="randy"

ENTRYPOINT ["java", "-jar", "/maven/${name}.jar"]

ARG name
ARG version

ADD target/${name}-${version}.jar /maven/${name}.jar

# from the base image, now need to setup the db
#RUN whatever
#

EXPOSE 8080