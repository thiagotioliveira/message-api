package com.thiagoti.messageapi.message.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostMessageDto {

    @NotBlank
    private String content;
    
}
