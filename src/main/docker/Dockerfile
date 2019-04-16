# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
# @autoor szh
FROM openjdk:11-jdk-oracle
VOLUME /tmp
#ARG JAR_FILE
#RUN apk add -U tzdata
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo "Asia/Shanghai" > /etc/timezone
#COPY ${JAR_FILE} app.jar
COPY app.jar app.jar
EXPOSE 8080
ENV LANG=en_US.UTF-8
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]