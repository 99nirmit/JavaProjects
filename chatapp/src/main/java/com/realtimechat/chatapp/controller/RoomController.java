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

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/create")
    public Room createRoom(@RequestBody RoomDTO roomDTO){
        Room room = new Room();
        room.setRoomId(roomDTO.getRoomId());
        room.setRoomName(roomDTO.getRoomName());
        return roomService.createRoom(room);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoom(@PathVariable Long id){
        try {
            Room room = roomService.getRoom(id);
            return ResponseEntity.ok(room);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/msg/{id}")
    public List<Messages> getMessagesOfRoom(@PathVariable Long id){
        return roomService.getMessageOfRoom(id);
    }
}
