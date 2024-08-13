FROM adoptopenjdk/openjdk17:alpine-jre
COPY ./target/Assessment-0.0.1-SNAPSHOT.jar /home/Assessment-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD java -jar /home/Assessment-0.0.1-SNAPSHOT.jar






