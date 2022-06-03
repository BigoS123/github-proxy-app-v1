# define base docker image
FROM openjdk:18
LABEL maitaner="Michal.L"
ADD target/github-proxy.jar springboot-docker.jar
ENTRYPOINT ["java","-jar","springboot-docker.jar"]