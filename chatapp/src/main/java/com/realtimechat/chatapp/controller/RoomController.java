package com.realtimechat.chatapp.controller;

import com.realtimechat.chatapp.models.Messages;
import com.realtimechat.chatapp.models.Room;
import com.realtimechat.chatapp.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/save")
    public Room createRoom(@RequestBody Long roomId){
        return roomService.createRoom(roomId);
    }

    @GetMapping("/{roomId}")
    public Room getRoom(@PathVariable Long roomId){
        return roomService.getRoom(roomId);
    }

    @GetMapping("/{roomId}")
    public List<Messages> getMessageOfRoom(@PathVariable Long roomId){
        return roomService.getMessageOfRoom(roomId);
    }
}
