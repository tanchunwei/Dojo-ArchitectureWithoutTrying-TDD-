FROM openjdk:8
COPY . .
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "/build/libs/Dojo-ArchitectureWithoutTrying-TDD--1.0-SNAPSHOT-boot.jar"]