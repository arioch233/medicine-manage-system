FROM openjdk:17
VOLUME /tmp
ADD mms-0.0.1.jar mms.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/mms.jar"]
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

