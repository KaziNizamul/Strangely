package com.strangely.backend.IntegrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.strangely.backend.Model.Chat.DTOs.MessageDTO;
import com.strangely.backend.Model.Chat.DTOs.MessageHistoryDTO;
import com.strangely.backend.Model.Chat.Entities.Message;
import com.strangely.backend.Service.Chat.Implementation.MessageService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MessageControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MessageService messageService;

    @MockBean
    private SimpMessagingTemplate messagingTemplate;

    @Test
    public void testSendMessage() throws Exception {
        // Create a sample message DTO
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setSender("user1");
        messageDTO.setReceiver("user2");
        messageDTO.setContent("Hello, WebSocket!");

        // Create a sample message entity
        Message sentMessage = new Message();
        sentMessage.setSender(messageDTO.getSender());
        sentMessage.setReceiver(messageDTO.getReceiver());
        sentMessage.setMessage(messageDTO.getContent());

        // Mock the messageService.sendMessage method
        when(messageService.sendMessage(any(MessageDTO.class))).thenReturn(sentMessage);

        // Perform the request using MockMvc
        MvcResult mvcResult = mockMvc.perform(post("/chat/send-message")
                        .content(objectMapper.writeValueAsString(messageDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.sender").value("user1"))
                .andExpect(jsonPath("$.receiver").value("user2"))
                .andExpect(jsonPath("$.message").value("Hello, WebSocket!"))
                .andReturn();

        // Optionally, you can perform additional assertions based on your specific use case

        // Verify that messagingTemplate.convertAndSend was called
        Mockito.verify(messagingTemplate, Mockito.times(1))
                .convertAndSend("/topic/public", sentMessage);
    }

    @Test
    public void testGetMessages() throws Exception {
        // Create a sample message history DTO
        MessageHistoryDTO messageHistoryDTO = new MessageHistoryDTO();
        messageHistoryDTO.setSender("user1");
        messageHistoryDTO.setReceiver("user2");

        // Create a sample list of messages
        List<Message> messages = Arrays.asList(
                new Message(1,"user1", "user2", "Hello", Instant. now().getEpochSecond() ),
                new Message(2, "user2", "user1", "Hi", Instant. now().getEpochSecond() ));

        // Mock the messageService.getMessages method
        when(messageService.getMessages(any(MessageHistoryDTO.class))).thenReturn(messages);

        // Perform the request using MockMvc
        mockMvc.perform(post("/chat/get-messages")
                        .content(objectMapper.writeValueAsString(messageHistoryDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].message").value("user1"))
                .andExpect(jsonPath("$[0].sender").value("user2"))
                .andExpect(jsonPath("$[0].receiver").value("Hello"))
                .andExpect(jsonPath("$[1].message").value("user2"))
                .andExpect(jsonPath("$[1].sender").value("user1"))
                .andExpect(jsonPath("$[1].receiver").value("Hi"))
                .andReturn();
    }
}

