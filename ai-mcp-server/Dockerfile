FROM amazoncorretto:21-alpine-jdk
LABEL authors="ruddy"

WORKDIR /app
COPY ./target/ai-mcp-server-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

ENV TZ=America/La_Paz

CMD ["java", "-jar", "ai-mcp-server-0.0.1-SNAPSHOT.jar", "-Xms128m -Xmx128m"]