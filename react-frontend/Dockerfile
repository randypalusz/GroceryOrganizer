# https://dev.to/karanpratapsingh/dockerize-your-react-app-4j2e
# TODO: have a separate production-ready image
FROM node:20.8.0-alpine as development
LABEL authors="randy"
ENV NODE_ENV development
# Add a work directory
WORKDIR /app
# Cache and Install dependencies
COPY package.json .
RUN npm install
# Copy app files
COPY . .
# Expose port
EXPOSE 3000
# Start the app
CMD [ "npm", "run", "dev" ]