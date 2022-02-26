FROM openjdk:11

COPY ["./target/activePayment-0.0.1-SNAPSHOT.jar", "/usr/app/"]

CMD ["java", "-jar", "/usr/app/activePayment-0.0.1-SNAPSHOT.jar"]

EXPOSE 8084