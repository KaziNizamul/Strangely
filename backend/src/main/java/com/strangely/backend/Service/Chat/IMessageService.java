package com.strangely.backend.Service.Chat;

import com.strangely.backend.Model.Chat.DTOs.MessageDTO;
import com.strangely.backend.Model.Chat.DTOs.MessageHistoryDTO;
import com.strangely.backend.Model.Chat.Entities.Message;

import java.util.List;

public interface IMessageService {

    public Message sendMessage(MessageDTO messageDTO);
    public List<Message> getMessages(MessageHistoryDTO messageHistoryDTO);
}
