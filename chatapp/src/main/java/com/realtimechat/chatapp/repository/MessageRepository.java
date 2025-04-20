package com.realtimechat.chatapp.repository;

import com.realtimechat.chatapp.models.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Messages, Long>{
}
