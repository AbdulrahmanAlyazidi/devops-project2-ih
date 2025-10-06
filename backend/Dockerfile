# ===== Build stage (Java 21 + Maven) =====
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# ===== Runtime stage (JRE 21) =====
FROM eclipse-temurin:21-jre
WORKDIR /app
# اذا يطلع اكثر من jar في target عدّل الاسم هنا
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
