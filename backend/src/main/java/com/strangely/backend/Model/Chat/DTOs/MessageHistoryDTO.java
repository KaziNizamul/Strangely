package com.strangely.backend.Model.Chat.DTOs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MessageHistoryDTO {
    String sender;
    String receiver;
}
