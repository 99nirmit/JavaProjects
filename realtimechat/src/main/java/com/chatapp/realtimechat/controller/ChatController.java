package com.chatapp.realtimechat.controller;

import com.chatapp.realtimechat.ChatMessageDTO;
import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/messages")
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/sendPublic")
    public ResponseEntity<String> sendMessage(@RequestBody ChatMessageDTO messageDTO){
        simpMessagingTemplate.convertAndSend("/topic/public", messageDTO);
        return ResponseEntity.ok("Message Sent Via Web Socket Channel");
    }

    @PostMapping("/sendPrivate")
    public ResponseEntity<?> sendPrivateMessage(@RequestBody ChatMessageDTO chatMessageDTO){
        //chatMessage.receiver = "rahul"
        simpMessagingTemplate.convertAndSend("/topic/private" + chatMessageDTO.getReceiver(), chatMessageDTO);
        return ResponseEntity.ok("Message sent to " + chatMessageDTO.getReceiver());
    }
}
