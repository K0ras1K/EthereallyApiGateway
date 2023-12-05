FROM openjdk:8-jre-alpine
LABEL authors="k0ras1k"

COPY build/libs/ETHEREALLY-API-0.0.1-all.jar main.jar

WORKDIR /

CMD ["java", "-jar", "main.jar"]