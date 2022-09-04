package com.thiagoti.messageapi.message;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thiagoti.messageapi.model.Message;

public interface MessageRepository extends JpaRepository<Message, UUID> {

}
