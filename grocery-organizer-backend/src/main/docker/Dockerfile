FROM eclipse-temurin:18-jre
LABEL authors="randy"

#ENTRYPOINT ["java", "-jar", "/maven/${name}.jar"]
# need to do this so that the mysql service is running automatically when the container starts
#ENTRYPOINT service mysql start && java -jar /maven/${name}.jar

ARG APP_NAME=${name}
#ARG PATH_TO_LOCAL_TARGET_DIR=${target_dir_path}
ARG APP_PATH=/maven/${APP_NAME}
ENV JAVA_APP_JAR=${APP_PATH}.jar

COPY target/${APP_NAME}-*.*.*-SNAPSHOT.jar ${APP_PATH}.jar
COPY target/classes/application.properties /maven/application.properties
COPY target/docker-extra/run-java/run-java.sh /

RUN chmod +x /run-java.sh

# Configure db
# TODO: don't hardcode the password...
#       also maybe use mysql_secure_installation binary to set some of this stuff up
#RUN service mysql start \
#    && mysql --execute="alter user 'root'@'localhost' identified with mysql_native_password by 'root1234'" \
#    && service mysql restart
# end configure db

#ENTRYPOINT java -jar /maven/GroceryOrganizer.jar --spring.config.location=file:/maven/application.properties
ENTRYPOINT /bin/sh -c ./run-java.sh run --spring-config.location=file:/maven/application.properties

EXPOSE 8080