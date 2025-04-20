package com.realtimechat.chatapp.controller;

import com.realtimechat.chatapp.DTO.RoomDTO;
import com.realtimechat.chatapp.models.Messages;
import com.realtimechat.chatapp.models.Room;
import com.realtimechat.chatapp.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Room> getRoom(@PathVariable String roomId){
        try {
            Room room = roomService.getRoom(roomId);
            return ResponseEntity.ok(room);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<Messages>> getMessagesOfRoom(@PathVariable String roomId,
                                                            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                            @RequestParam(value = "size", defaultValue = "20", required = false) int size){
        List<Messages> messages = roomService.getMessageOfRoom(roomId);
        int start = Math.max(0, messages.size() - (page + 1) * size);
        int end = Math.min(messages.size(), start + size);
        List<Messages> paginatedMessages = messages.subList(start, end);
        return ResponseEntity.ok(paginatedMessages);
    }
}
