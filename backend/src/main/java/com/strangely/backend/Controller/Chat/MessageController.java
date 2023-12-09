package com.strangely.backend.Controller.Chat;

import com.strangely.backend.Model.Chat.DTOs.MessageDTO;
import com.strangely.backend.Model.Chat.DTOs.MessageHistoryDTO;
import com.strangely.backend.Model.Chat.Entities.Message;
import com.strangely.backend.Service.Chat.IMessageService;
import com.strangely.backend.Service.Chat.Implementation.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/chat")
@CrossOrigin
public class MessageController {

    private final IMessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public MessageController(MessageService messageService, SimpMessagingTemplate messagingTemplate) {
        this.messageService = messageService;
        this.messagingTemplate = messagingTemplate;
    }

    // This method is a POST mapping for "/send-message", responsible for sending a message.
// The CrossOrigin annotation is used to handle Cross-Origin Resource Sharing (CORS) for this endpoint.
    @PostMapping("/send-message")
    @CrossOrigin
    public ResponseEntity<Message> sendMessage(@RequestBody MessageDTO messageDto) {
        // Initialize the response entity to be populated based on the outcome of the operation.
        ResponseEntity response = null;

        try {
            // Attempt to send a message using the provided MessageDTO through the messageService.
            Message sentMessage = messageService.sendMessage(messageDto);

            // Notify subscribers about the sent message using WebSocket ("/topic/public").
            messagingTemplate.convertAndSend("/topic/public", sentMessage);

            // Create a 202 ACCEPTED response with the sent message in the response body.
            response = ResponseEntity.status(HttpStatus.ACCEPTED).body(sentMessage);
        } catch (Exception e) {
            // If an exception (e.g., any generic Exception) is caught during the operation:

            // Generate a 400 BAD REQUEST response with the exception details in the response body.
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
        return response;
    }


    // This method is a POST mapping for "/get-messages", designed to retrieve messages between two users.
    // The messages are returned in descending order of timestamp.
    @PostMapping("/get-messages")
    public ResponseEntity<List<Message>> getMessages(@RequestBody MessageHistoryDTO mhdto) {
        // Initialize the response entity to be populated based on the outcome of the operation.
        ResponseEntity response = null;

        try {
            // Attempt to retrieve messages between two users based on the provided MessageHistoryDTO through the messageService.
            List<Message> messages = messageService.getMessages(mhdto);

            // If messages are successfully retrieved, create a 200 OK response with the list of messages in the response body.
            response = ResponseEntity.status(HttpStatus.OK).body(messages);
        } catch (Exception e) {
            // If an exception (e.g., any generic Exception) is caught during the operation:
            // Generate a 400 BAD REQUEST response with the exception details in the response body.
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }

        return response;
    }

}

