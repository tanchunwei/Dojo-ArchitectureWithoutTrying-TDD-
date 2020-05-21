#### Require to download mongodb-mms.deb files
```
https://www.mongodb.com/download-center/ops-manager?jmp=docs

Choose 
 version: 4.2.13
 Platforms: Debian 9/ Ubuntu 16.04 + 18.04
 Packages: DEB
```

#### Require to change Dockerfile
```
RUN dpkg --install installation/<mongo ops manage filename>.deb
```
