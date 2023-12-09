package com.strangely.backend.Service.Chat.Implementation;

import com.strangely.backend.Model.Chat.DTOs.MessageDTO;
import com.strangely.backend.Model.Chat.DTOs.MessageHistoryDTO;
import com.strangely.backend.Model.Chat.Entities.Message;
import com.strangely.backend.Model.User.Entities.User;
import com.strangely.backend.Repository.Chat.MessageRepository;
import com.strangely.backend.Repository.User.UserRepository;
import com.strangely.backend.Service.Chat.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class MessageService implements IMessageService {

    @Autowired
    private final MessageRepository messageRepository;
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    // Send a message from one user (sender) to another user (receiver).
    @Override
    public Message sendMessage(MessageDTO messageDTO) {
        User sender = userRepository.findByUsername(messageDTO.getSender()).orElse(null);
        User receiver = userRepository.findByUsername(messageDTO.getReceiver()).orElse(null);
        if (sender == null) {
            System.out.println("Sender not found");
        }
        if (receiver == null) {
            System.out.println("Receiver not found");
        }
        Message message = new Message();
        // Set the sender username in the message if the sender user exists.
        if (sender != null) {
            message.setSender(sender.getUsername());
        }
        // Set the receiver username in the message if the receiver user exists.
        if (receiver != null) {
            message.setReceiver(receiver.getUsername());
        }
        // Set the message content from the provided MessageDTO.
        message.setMessage(messageDTO.getContent());
        // Set the timestamp of the message to the current epoch second.
        message.setTimestamp(Instant.now().getEpochSecond());
        return messageRepository.save(message);
    }


    // Retrieve messages between two users in descending order based on timestamps.
    @Override
    public List<Message> getMessages(MessageHistoryDTO messageHistoryDTO) {
        User sender = userRepository.findByUsername(messageHistoryDTO.getSender()).orElse(null);
        User receiver = userRepository.findByUsername(messageHistoryDTO.getReceiver()).orElse(null);

        // Check if sender user exists.
        if (sender == null) {
            System.out.println("Sender not found");
            return null;
        }

        // Check if receiver user exists.
        if (receiver == null) {
            System.out.println("Receiver not found");
            return null;
        }
        List<Message> messages = new ArrayList<>();

        // Retrieve messages sent from receiver to sender and sender to receiver.
        messages.addAll(messageRepository.findByReceiverAndSender(receiver.getUsername(), sender.getUsername()));
        messages.addAll(messageRepository.findByReceiverAndSender(sender.getUsername(), receiver.getUsername()));

        // Sort the messages in descending order based on their timestamps.
        messages.sort(Comparator.comparing(Message::getTimestamp).reversed());
        return messages;
    }

}
