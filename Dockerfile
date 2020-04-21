FROM openjdk:8
COPY . .
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "/build/libs/Dojo-ArchitectureWithoutTrying-TDD--1.0-SNAPSHOT-boot.jar"]