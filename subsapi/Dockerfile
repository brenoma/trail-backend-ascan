FROM openjdk:11

ARG PROFILE
ARG ADDITIONAL_OPTS

ENV PROFILE=${PROFILE}
ENV ADDITIONAL_OPTS=${ADDITIONAL_OPTS}

WORKDIR /opt/subsapi

#COPY entrypoint.sh entrypoint.sh
#
#COPY .env.example .env.example
#
#RUN chmod +x ./entrypoint.sh

#RUN ./entrypoint.sh

COPY /target/subsapi*.jar app.jar

SHELL ["/bin/sh", "-c"]

CMD java ${ADDITIONAL_OPTS} -jar app.jar --spring.profiles.active=${PROFILE}