package com.thiagoti.messageapi.contacts;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thiagoti.messageapi.bot.client.WhatsAppClient;
import com.thiagoti.messageapi.contacts.dto.ContactDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactServiceImpl implements ContactService {

    private final WhatsAppClient whatsAppClient;
    
    @Override
    public List<ContactDto> getAll() throws Exception {
        log.debug("get all contacts.");
        return whatsAppClient.getAllContacts();
    }

}
