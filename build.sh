./gradlew bootJar
sudo docker build -f Dockerfile -t pos:1.0 .
sudo docker build -f Dockerfile -t pos:latest .