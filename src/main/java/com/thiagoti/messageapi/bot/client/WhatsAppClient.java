package com.thiagoti.messageapi.bot.client;

import java.util.List;

import com.thiagoti.messageapi.contacts.dto.ContactDto;

public interface WhatsAppClient {
    
    List<ContactDto> getAllContacts() throws Exception;
    void sendText(String to, String content) throws Exception;
    boolean isLoggedIn();
}
