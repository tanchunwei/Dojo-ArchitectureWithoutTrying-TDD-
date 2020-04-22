FROM openjdk:8
RUN mkdir -p /pos-web
COPY . /pos-web/
WORKDIR /pos-web/
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "build/libs/Dojo-ArchitectureWithoutTrying-TDD--1.0-SNAPSHOT-boot.jar"]