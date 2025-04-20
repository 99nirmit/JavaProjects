package com.realtimechat.chatapp.services;
import com.realtimechat.chatapp.models.Messages;
import com.realtimechat.chatapp.models.Room;
import com.realtimechat.chatapp.repository.RoomRepository;
import jakarta.transaction.Transactional;
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

        if(roomRepository.existsByRoomId(room.getRoomId())){
            throw new IllegalArgumentException("Room Already Exits");
        }
        return roomRepository.save(room);
    }


    //getRoom

    public Room getRoom(Long roomId) {
        return roomRepository.findByRoomId(roomId);
    }

    //get messages of room

    @Transactional
    public List<Messages> getMessageOfRoom(Long roomId) {
        Room roomById = roomRepository.findByRoomId(roomId);
        return roomById.getMessages();
    }

}
