package com.thiagoti.messageapi.message;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiagoti.messageapi.message.dto.MessageDto;
import com.thiagoti.messageapi.message.dto.PatchMessageDto;
import com.thiagoti.messageapi.message.dto.PostMessageDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService service;
    
    @GetMapping
    public List<MessageDto> getAll(){
        return service.getAll();
    }
    
    @PostMapping
    public MessageDto postMessage(@Valid @RequestBody PostMessageDto postMessageDto) {
        return service.create(postMessageDto.getContent());
    }
    
    @PatchMapping("/{id}")
    public MessageDto patchMessage(@PathVariable UUID id, @Valid @RequestBody PatchMessageDto patchMessageDto) {
        return service.updateById(id, patchMessageDto.getContent());
    }
    
    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable UUID id) {
        service.deleteById(id);
    }
}
