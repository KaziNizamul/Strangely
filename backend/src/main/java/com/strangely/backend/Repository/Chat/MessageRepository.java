package com.strangely.backend.Repository.Chat;

import com.strangely.backend.Model.Chat.Entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByReceiverAndSender(String  receiver, String  sender);
}