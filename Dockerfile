FROM maven:3.8.6-jdk-8-slim as build
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml package

FROM tomcat:9.0.0-jre8
COPY --from=build /app/target/"GermanPhoneParser-0.0.1-SNAPSHOT.war" /usr/local/tomcat/webapps/"api.war"
EXPOSE 8000



