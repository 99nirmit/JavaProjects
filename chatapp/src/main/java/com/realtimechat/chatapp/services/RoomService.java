package com.realtimechat.chatapp.services;
import com.realtimechat.chatapp.models.Messages;
import com.realtimechat.chatapp.models.Room;
import com.realtimechat.chatapp.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    //create Room
    public Room createRoom(Room room){
        roomRepository.save(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(room).getBody();
    }


    //getRoom

    public Room getRoom(Long roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room Not Found By this id" + roomId));
    }

    //get messages of room

    public List<Messages> getMessageOfRoom(Long roomId) {
        Optional<Room> room = roomRepository.findById(roomId);
        return ResponseEntity.of(room).getBody().getMessages();
    }

}
