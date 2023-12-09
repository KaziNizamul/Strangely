package com.strangely.backend.UnitTest.Service.Chat;

import com.strangely.backend.Model.Chat.DTOs.MessageDTO;
import com.strangely.backend.Model.Chat.DTOs.MessageHistoryDTO;
import com.strangely.backend.Model.Chat.Entities.Message;
import com.strangely.backend.Model.User.Entities.User;
import com.strangely.backend.Repository.Chat.MessageRepository;
import com.strangely.backend.Repository.User.UserRepository;
import com.strangely.backend.Service.Chat.Implementation.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MessageService messageService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendMessage() {
        // Create a sample MessageDTO
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setSender("senderUsername");
        messageDTO.setReceiver("receiverUsername");
        messageDTO.setContent("Hello");

        // Mock UserRepository to return a user when findByUsername is called
        User senderUser = new User();
        senderUser.setUsername("senderUsername");

        User receiverUser = new User();
        receiverUser.setUsername("receiverUsername");

        when(userRepository.findByUsername("senderUsername")).thenReturn(Optional.of(senderUser));
        when(userRepository.findByUsername("receiverUsername")).thenReturn(Optional.of(receiverUser));

        // Mock MessageRepository to return a saved message when save is called
        when(messageRepository.save(any(Message.class))).thenReturn(new Message());

        // Call the sendMessage method
        Message result = messageService.sendMessage(messageDTO);

        assertNull(result);
    }
    @Test
    public void testSendMessageWithInvalidSender() {
        // Create a sample MessageDTO with an invalid sender
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setSender("invalidSender");
        messageDTO.setReceiver("receiverUsername");
        messageDTO.setContent("Hello");

        // Mock UserRepository to return null when findByUsername is called for the sender
        when(userRepository.findByUsername("invalidSender")).thenReturn(Optional.empty());

        // Call the sendMessage method
        Message result = messageService.sendMessage(messageDTO);

        // Add assertions as needed, for example, assert that the result is null
        assertNull(result);
    }

    // Add similar test cases for invalid receiver, edge cases, etc.

    @Test
    public void testGetMessages() {
        // Create a sample MessageHistoryDTO
        MessageHistoryDTO messageHistoryDTO = new MessageHistoryDTO();
        messageHistoryDTO.setSender("senderUsername");
        messageHistoryDTO.setReceiver("receiverUsername");

        // Mock UserRepository to return users when findByUsername is called
        when(userRepository.findByUsername("senderUsername")).thenReturn(Optional.of(new User()));
        when(userRepository.findByUsername("receiverUsername")).thenReturn(Optional.of(new User()));

        // Mock MessageRepository to return a list of messages when findByReceiverAndSender is called
        when(messageRepository.findByReceiverAndSender("receiverUsername", "senderUsername"))
                .thenReturn(Collections.singletonList(new Message()));

        // Call the getMessages method
        List<Message> result = messageService.getMessages(messageHistoryDTO);

        assertNull(result);
    }

    @Test
    public void testGetMessagesWithInvalidSender() {
        // Create a sample MessageHistoryDTO with an invalid sender
        MessageHistoryDTO messageHistoryDTO = new MessageHistoryDTO();
        messageHistoryDTO.setSender("invalidSender");
        messageHistoryDTO.setReceiver("receiverUsername");

        // Mock UserRepository to return null when findByUsername is called for the sender
        when(userRepository.findByUsername("invalidSender")).thenReturn(Optional.empty());

        // Call the getMessages method
        List<Message> result = messageService.getMessages(messageHistoryDTO);


        // Add assertions as needed, for example, assert that the result is null
        assertNull(result);
    }

}