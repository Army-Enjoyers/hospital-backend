FROM gradle:6.8.3-jdk8 as build

WORKDIR /app

COPY . /app

RUN gradle bootWar --no-daemon

FROM tomcat:latest as runner
WORKDIR .

COPY --from=build app/build/libs/hosp.war /usr/local/tomcat/webapps/ROOT.war