# KOM HIER NOOIT AAN!
FROM maven:3.8.4-eclipse-temurin-17-alpine as build

WORKDIR /usr/src/app
COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B
COPY ./src ./src
RUN mvn package -DskipTests

FROM tomcat:9
COPY --from=build /usr/src/app/target/*.war /usr/local/tomcat/webapps/ROOT.war
LABEL org.opencontainers.image.description="Docker image to be deployed"
LABEL org.opencontainers.image.title="Projectweek 2021"
LABEL org.opencontainers.image.authors="Jesse Storms"
CMD ["catalina.sh", "run"]