package com.thiagoti.messageapi.message;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.thiagoti.messageapi.message.dto.MessageDto;
import com.thiagoti.messageapi.model.Message;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository repository;
    
    private final MessageMapper mapper;
    
    @Override
    public List<MessageDto> getAll() {
        return repository.findAll().stream().map(m -> mapper.toDto(m)).collect(Collectors.toList());
    }

    @Override
    public Optional<MessageDto> getById(UUID id) {
        Optional<Message> findById = repository.findById(id);
        return findById.isPresent() ? Optional.of(mapper.toDto(findById.get())) : Optional.empty();
    }

    @Override
    public MessageDto create(String content) {
        return mapper.toDto(repository.save(Message.builder().content(content).build()));
    }

    @Override
    public MessageDto updateById(UUID id, String content) {
        Message target = repository.findById(id).orElseThrow(IllegalArgumentException::new);
        target.setContent(content);
        return mapper.toDto(repository.save(target));
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

}
