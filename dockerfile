# build
FROM openjdk:17-bullseye

RUN apt update
RUN apt install maven -y
