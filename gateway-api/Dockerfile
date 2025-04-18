FROM node:latest AS base

# Set the working directory within the container
WORKDIR /app

COPY package.json ./

COPY package-lock.json ./

RUN npm install --include=dev

COPY . .

ENV NODE_ENV=development

EXPOSE 3000

CMD ["npm", "run", "dev"]
