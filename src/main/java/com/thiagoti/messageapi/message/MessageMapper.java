package com.thiagoti.messageapi.message;

import org.mapstruct.Mapper;

import com.thiagoti.messageapi.message.dto.MessageDto;
import com.thiagoti.messageapi.model.Message;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    MessageDto toDto(Message message);
    
}
