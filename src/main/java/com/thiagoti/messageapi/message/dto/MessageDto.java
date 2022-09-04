package com.thiagoti.messageapi.message.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageDto {

    private UUID id;
    
    private String content;
    
}
