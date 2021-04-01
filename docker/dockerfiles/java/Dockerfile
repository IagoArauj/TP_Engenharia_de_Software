FROM openjdk:11

ARG PROFILE
ARG ADITIONAL_OPTS

ENV PROFILE=${PROFILE}
ENV ADITIONAL_OPTS=${ADITIONAL_OPTS}

WORKDIR /opt/spring

COPY /target/modulo5.jar modulo5.jar

SHELL ["bin/sh", "-c"]

EXPOSE 5005
EXPOSE 8080

CMD java ${ADITIONAL_OPTS} -jar modulo5.jar --spring.profiles.active=${PROFILE}
