package com.thiagoti.messageapi.bot.client.bot.venom;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thiagoti.messageapi.bot.client.WhatsAppClient;
import com.thiagoti.messageapi.bot.client.bot.venom.components.GetContact;
import com.thiagoti.messageapi.contacts.ContactMapper;
import com.thiagoti.messageapi.contacts.dto.ContactDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class VenomBotClient implements WhatsAppClient {

    @Value("${app.whatsapp-bot.server}")
    private String whatsAppServer;
    
    private final ObjectMapper objectMapper;
    
    private final ContactMapper contactMapper;
    
    @Override
    public void sendText(String to, String content) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s/send", whatsAppServer)))
                .POST(HttpRequest.BodyPublishers.ofString(String.format("{\"to\":\"%s\",\"content\":\"%s\"}", to, content)))
                .header("Content-type", MediaType.APPLICATION_JSON_VALUE)
                .build();
        
        client.send(request, HttpResponse.BodyHandlers.ofString());
        log.debug("message sent.");
    }

    @Override
    public boolean isLoggedIn() {
        boolean loggedin = false;
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(String.format("%s/health", whatsAppServer)))
                    .GET()
                    .header("Content-type", MediaType.APPLICATION_JSON_VALUE)
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            loggedin = HttpStatus.OK.value() == response.statusCode();
        } catch (Exception e) {
            loggedin = false;
        }
        log.debug("loggedin ? {}", loggedin);
        return loggedin;
    }

    @Override
    public List<ContactDto> getAllContacts() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s/contacts", whatsAppServer)))
                .GET()
                .header("Content-type", MediaType.APPLICATION_JSON_VALUE)
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        List<GetContact> contacts = objectMapper.readValue(response.body(), new TypeReference<List<GetContact>>(){});
        return contacts.stream().map(c -> contactMapper.toDto(c)).collect(Collectors.toList());
    }
}
