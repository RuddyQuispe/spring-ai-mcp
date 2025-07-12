package edu.bo.uyunicode.ai.mcp.server.config;

import edu.bo.uyunicode.ai.mcp.server.tools.ProductToolSet;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ToolSpecifications {
    @Bean
    public List<ToolCallback> loadTools(ProductToolSet weatherToolSet) {
        return List.of(ToolCallbacks.from(weatherToolSet));
    }
}
