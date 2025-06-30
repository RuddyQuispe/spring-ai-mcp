package edu.bo.uyunicode.ai.mcp.server.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.logging.Logger;


@Component
public class WeatherToolSet {

    private final Logger LOGGER = Logger.getLogger(WeatherToolSet.class.getName());

    @Tool(name = "getWeather", description = "obtener la temperatura del clima")
    public String getWeather(@ToolParam(description = "city", required = true) String city) {
        String sessionId = UUID.randomUUID().toString();
        LOGGER.info("[%s]: Obteniendo la clima del cliente %s".formatted(sessionId, city));
        return "El clima en %s es de 20°C".formatted(city);
    }

    @Tool(name = "greater", description = "saluda el servidor mcp")
    public String greater(@ToolParam(description = "nombre", required = true) String userName) {
        String sessionId = UUID.randomUUID().toString();
        LOGGER.info("[%s]: Saludando al usuario %s".formatted(sessionId, userName));
        return "Hola, como estas %s, te saluda el servidor MCP [%s]".formatted(userName, sessionId);
    }
}
