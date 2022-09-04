package com.thiagoti.messageapi.contacts;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.thiagoti.messageapi.bot.client.bot.venom.components.GetContact;
import com.thiagoti.messageapi.contacts.dto.ContactDto;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    
    @Mapping(source = "src.id._serialized", target = "id")
    @Mapping(source = "src.displayName", target = "name")
    ContactDto toDto(GetContact src);
    
}
