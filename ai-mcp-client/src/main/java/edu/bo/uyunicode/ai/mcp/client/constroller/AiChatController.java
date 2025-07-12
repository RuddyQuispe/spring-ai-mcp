package edu.bo.uyunicode.ai.mcp.client.constroller;

import edu.bo.uyunicode.ai.mcp.client.model.MessageDto;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.logging.Logger;

@RequestMapping("/ai-chat")
@RestController
public class AiChatController {
    private ChatClient chatClient;
    private SyncMcpToolCallbackProvider syncMcpToolCallbackProvider;
    private final Logger LOGGER = Logger.getLogger(AiChatController.class.getName());

    @Autowired
    public AiChatController(ChatClient.Builder chatClientBuilder,
                            @Qualifier("mcp-server-callback-tool") SyncMcpToolCallbackProvider syncMcpToolCallbackProvider) {
        this.chatClient = chatClientBuilder.build();
        this.syncMcpToolCallbackProvider = syncMcpToolCallbackProvider;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendMessage(@RequestBody MessageDto request) {
        String sessionId = UUID.randomUUID().toString();
        LOGGER.info("[%s]: send chat message to ai model %s".formatted(sessionId, request));
        String response = this.chatClient.prompt()
                .user(request.message())
                .toolCallbacks(this.syncMcpToolCallbackProvider)
                .call()
                .content();
        LOGGER.info("[%s]: response send chat message to ai model %s".formatted(sessionId, response));
        return ResponseEntity.ok(response);
    }
}
