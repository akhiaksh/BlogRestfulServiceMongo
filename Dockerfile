FROM openjdk:17-alpine
WORKDIR usr/src
ENV MONGO_URL=mongodb://mongocontainer:27017/Blogdb
ENV MONGO_DB=Blogdb
ADD ./target/BlogRestfulServiceMongo-0.0.1-SNAPSHOT.jar /usr/src/BlogRestfulServiceMongo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongocontainer:27017/Blogdb","-jar","BlogRestfulServiceMongo-0.0.1-SNAPSHOT.jar"]