FROM eclipse-temurin:21.0.4_7-jdk
EXPOSE 8083
WORKDIR /root
COPY ./pom.xml /root
COPY ./.mvn /root/.mvn
COPY ./mvnw /root
RUN ./mvnw dependency:go-offline
COPY ./src /root/src
RUN ./mvnw clean install -DskipTests
ENTRYPOINT ["java","-jar","/root/target/product-service-api-0.0.1-SNAPSHOT.jar"]