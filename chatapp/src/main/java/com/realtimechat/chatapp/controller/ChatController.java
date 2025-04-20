package com.realtimechat.chatapp.controller;

import com.realtimechat.chatapp.DTO.MessagesDTO;
import com.realtimechat.chatapp.models.Messages;
import com.realtimechat.chatapp.models.Room;
import com.realtimechat.chatapp.repository.MessageRepository;
import com.realtimechat.chatapp.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:5173")
public class ChatController  {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MessageRepository messageRepository;

    @MessageMapping("/sendMessage/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public Messages sendMessages(@DestinationVariable String roomId, @RequestBody MessagesDTO messagesDTO){

        Room room = roomRepository.findByRoomId(messagesDTO.getRoomId());
        Messages messages = new Messages();
        messages.setContent(messagesDTO.getContent());
        messages.setSender(messagesDTO.getSender());
        messages.setTimeStamp(LocalDateTime.now());
        messages.setRoom(room);

        if(room != null){
            room.getMessages().add(messages);
            messageRepository.save(messages);
            roomRepository.save(room);
        }else{
            throw new RuntimeException("Room Not Found") ;
        }

        return messages;

    }
}
