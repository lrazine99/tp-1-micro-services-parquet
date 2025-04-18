FROM node:latest AS base

WORKDIR /app

COPY package.json ./

COPY package-lock.json ./

RUN npm install --include=dev
RUN npm install -g nodemon

COPY . .

ENV NODE_ENV=development

EXPOSE 3000

FROM base AS dev

CMD ["npm", "run", "dev"]

FROM base AS prod

COPY . .

ENV NODE_PATH=./build

RUN npm run build

EXPOSE 3000

CMD ["npm", "run", "start"]