package com.realtimechat.chatapp.repository;

import com.realtimechat.chatapp.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findByRoomId(Long roomId);
}
