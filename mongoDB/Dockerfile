FROM ubuntu:18.04

#User single run command to limit multiple layer of image created
RUN apt-get update && apt-get install -y \
    systemd \
    sudo

COPY ./installation /installation/
RUN dpkg --install installation/mongodb-mms_4.2.13.56889.20200513T1941Z-1_x86_64.deb