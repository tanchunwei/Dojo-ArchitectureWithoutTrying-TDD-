FROM node:13
RUN mkdir -p /web-frontend
WORKDIR /web-frontend
copy package.json yarn.lock ./
RUN yarn
COPY . ./
EXPOSE 9100

RUN yarn build
ENTRYPOINT ["yarn", "start:docker"]
# Temporary need to build multiple images for different env. (TODO Research a way to use 1 images for different env)
#ENTRYPOINT ["yarn", "start:k8s"]
#ENTRYPOINT ["yarn", "start"]