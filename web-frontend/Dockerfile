FROM node:13
RUN mkdir -p /web-frontend
WORKDIR /web-frontend
copy package.json yarn.lock ./
RUN yarn
COPY . ./
EXPOSE 9100
RUN yarn build

ENTRYPOINT ["yarn", "start:docker"]