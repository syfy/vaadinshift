FROM java:8
EXPOSE 8080
ADD /target/projectel-0.0.1-SNAPSHOT.jar projectel-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","projectel-0.0.1-SNAPSHOT.jar"]