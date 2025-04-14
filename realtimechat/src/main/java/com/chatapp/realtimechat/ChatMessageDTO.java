package com.chatapp.realtimechat;

import lombok.Data;

@Data
public class ChatMessageDTO {

    private String sender;
    private String receiver;
    private String content;
    private String timestamp;
}
