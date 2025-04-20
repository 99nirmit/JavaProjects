package com.realtimechat.chatapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sender;

    private String content;

    private LocalDateTime timeStamp;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonIgnore
    private Room room;

    @PrePersist
    public void prePersist() {
        if(this.timeStamp == null){
            this.timeStamp = LocalDateTime.now();
        }
    }


    public Messages(String sender, String content){
        this.sender = sender;
        this.content = content;
        this.timeStamp = LocalDateTime.now();
    }
}
