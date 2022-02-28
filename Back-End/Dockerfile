FROM openjdk:11
WORKDIR /opt
ENV PORT 9096
EXPOSE 9096
COPY target/*.jar /opt/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar