./gradlew bootJar
#Do note to run in root folder as the COPY in Dockerfile start from root
sudo docker build -f docker-scripts/Dockerfile -t pos:1.0 .
sudo docker build -f docker-scripts/Dockerfile -t pos:latest .