FROM openjdk:11

ARG PROFILE
ARG ADDITIONAL_OPTS

ENV PROFILE=${PROFILE}
ENV ADDITIONAL_OPTS=${ADDITIONAL_OPTS}

WORKDIR /opt/subs-api

COPY /target/subs-api*.jar app.jar

RUN chmod -x app.jar

SHELL ["/bin/sh", "-c"]

#CMD java ${ADDITIONAL_OPTS} -jar app.jar --spring.profiles.active=${PROFILE}

ENTRYPOINT ["java", "-jar", "app.jar"]