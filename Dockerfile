FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

RUN chmod +x ./mvnw

RUN ./mvnw clean package -DskipTests

EXPOSE 8081mvn clean package -DskipTests

CMD ["sh", "-c", "java -jar target/*.jar"]