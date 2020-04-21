#example of how to build image from Dockerfile
#sudo docker build -f Dockerfile -t pos-1.0 .

#example to remove untagged images (happen when build image with same name, the old image will be untagged)
#sudo docker rmi $(sudo docker images -f"dangling=true" -q)

#example of how to create container from images
#sudo docker container create --name pos-s1 -p 8090:8080 pos-1.0

sudo docker-compose up --scale web=2 -d