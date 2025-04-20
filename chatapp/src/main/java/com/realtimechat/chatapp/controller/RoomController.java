package com.realtimechat.chatapp.controller;

import com.realtimechat.chatapp.DTO.RoomDTO;
import com.realtimechat.chatapp.models.Messages;
import com.realtimechat.chatapp.models.Room;
import com.realtimechat.chatapp.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin("http://localhost:5173")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/create")
    public  ResponseEntity<Room> createRoom(@RequestBody RoomDTO roomDTO){
        Room room = new Room();
        room.setRoomId(roomDTO.getRoomId());
        room.setRoomName(roomDTO.getRoomName());
        Room savedRoom = roomService.createRoom(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRoom);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<Room> getRoom(@PathVariable Long roomId){
        try {
            Room room = roomService.getRoom(roomId);
            return ResponseEntity.ok(room);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<Messages>> getMessagesOfRoom(@PathVariable Long roomId){
        List<Messages> messageOfRoom = roomService.getMessageOfRoom(roomId);
        return ResponseEntity.of(Optional.ofNullable(messageOfRoom));
    }
}
