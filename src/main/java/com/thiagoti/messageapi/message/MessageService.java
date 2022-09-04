package com.thiagoti.messageapi.message;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.thiagoti.messageapi.message.dto.MessageDto;

public interface MessageService {

    List<MessageDto> getAll();
    
    Optional<MessageDto> getById(UUID id);
    
    MessageDto create(String body);
    
    MessageDto updateById(UUID id, String body);
    
    void deleteById(UUID id);
    
}
