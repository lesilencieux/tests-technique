#
# Build stage
#
FROM maven:3.8.5-openjdk-17 AS build
COPY src /home/app/src/
COPY pom.xml /home/app/
RUN mvn -f /home/app/pom.xml clean package -DskipTests

#
# Package stage
#

FROM openjdk:17
COPY --from=build /home/app/target/java-0.0.1-SNAPSHOT.jar java-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar","java-0.0.1-SNAPSHOT.jar"]
