FROM openjdk:17-jdk-slim-bullseye as build
WORKDIR /workspace/app

COPY ./crypto-exchange/mvnw .
COPY ./crypto-exchange/.mvn .mvn
COPY ./crypto-exchange/pom.xml .
COPY ./crypto-exchange/src src

RUN apt-get update && apt-get install dos2unix
RUN dos2unix mvnw
RUN ./mvnw install -U -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*-0.0.1-SNAPSHOT.jar)

FROM openjdk:17-jdk-slim-bullseye
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","me.darko.cryptoexchange.CryptoExchangeApplication"]

