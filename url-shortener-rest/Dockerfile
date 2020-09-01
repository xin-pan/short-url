FROM amazoncorretto:8u265
COPY ./target/url-shortener-rest-1.0-SNAPSHOT.jar /usr/local/app.jar
ENV JVM_OPTION=""
EXPOSE 8080
ENTRYPOINT java ${JVM_OPTION} -jar /usr/local/app.jar
