package com.strangely.backend.Model.Chat.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MessageDTO {
    private String sender;
    private String receiver;
    private String content;
}