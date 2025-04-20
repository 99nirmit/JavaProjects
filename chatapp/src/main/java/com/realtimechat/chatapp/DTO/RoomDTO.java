package com.realtimechat.chatapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {

    private Long id;

    private String roomId;

    private String roomName;
}
