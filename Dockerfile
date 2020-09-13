FROM openjdk:8-jdk-alpine

RUN mkdir /var/circleci-with-springboot

ARG DEPENDENCY=build
ADD ${DEPENDENCY}/libs/spring-boot-demo-0.0.1.jar /var/spring-boot-demo/springbootdemo.jar

EXPOSE 8083

ENTRYPOINT ["java","-jar","/var/spring-boot-demo/springbootdemo.jar"]