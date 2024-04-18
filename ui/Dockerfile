# # Use an official Node.js 18.x runtime as a parent image
# FROM node:18.18

# WORKDIR /app
# COPY package.json yarn.lock ./
# RUN yarn install --network-timeout 600000
# COPY . .
# EXPOSE 5173
# ENTRYPOINT ["yarn", "dev"]

# Stage 1: Build the application
FROM node:18.20.2-alpine3.18 AS build
WORKDIR /app
COPY package.json yarn.lock ./
RUN yarn install --network-timeout 600000
COPY . .
# Add any build steps here
RUN yarn build

# Stage 2: Create the final lightweight image
FROM node:18.20.2-alpine3.18
WORKDIR /app
COPY --from=build /app .
EXPOSE 5173
ENTRYPOINT ["yarn", "dev"]
