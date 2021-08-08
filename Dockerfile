FROM java:8

RUN   ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

COPY target/*.jar /helpcode.jar
EXPOSE 9999

CMD ["java","-jar", "/helpcode.jar"]
