package com.thiagoti.messageapi.contacts;

import java.util.List;

import com.thiagoti.messageapi.contacts.dto.ContactDto;

public interface ContactService {

    List<ContactDto> getAll() throws Exception;
    
}
