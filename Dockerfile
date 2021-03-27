#
#
#FROM tomcat:8.0-alpine
#
#LABEL maintainer=ilboogl@gmail.com
#
#ADD sample.war /usr/local/tomcat/webapps/
#
#EXPOSE 8080
#
#CMD [“catalina.sh”, “run”]
#
#//////////////////////////////////////////////////////////////////
FROM gradle:6.8.3-jdk8 as build

WORKDIR /app

COPY . /app

RUN gradle web:bootWar

FROM tomcat:latest as runner
WORKDIR .

COPY --from=build /app/web/build/libs/hosp.war /usr/local/tomcat/webapps/