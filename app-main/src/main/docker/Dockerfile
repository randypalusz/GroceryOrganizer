FROM randypalusz/grocery-organizer-base:latest
LABEL authors="randy"

#ENTRYPOINT ["java", "-jar", "/maven/${name}.jar"]
# need to do this so that the mysql service is running automatically when the container starts
ENTRYPOINT service mysql start && java -jar /maven/${name}.jar

ARG name
ARG version

ADD target/${name}-${version}.jar /maven/${name}.jar

# Configure db
# TODO: don't hardcode the password...
#       also maybe use mysql_secure_installation binary to set some of this stuff up
RUN service mysql start \
    && mysql --execute="alter user 'root'@'localhost' identified with mysql_native_password by 'root1234'" \
    && service mysql restart
# end configure db

EXPOSE 8080