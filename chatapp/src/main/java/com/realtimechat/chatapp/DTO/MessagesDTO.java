package com.realtimechat.chatapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessagesDTO {

    private Long id;

    private String sender;

    private String content;

    private Long roomId;

}
