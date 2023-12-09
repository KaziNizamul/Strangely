package com.strangely.backend.Controller.Chat;

import com.strangely.backend.Model.Chat.Entities.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/chat.sendMessage") // Listen for messages at this destination
    @SendTo("/topic/public") // Send messages to this topic
    public Message sendMessage(Message message) {
        // Process the message (e.g., save it to the database)
        return message;
    }
}

