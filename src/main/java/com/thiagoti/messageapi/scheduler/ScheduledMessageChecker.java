package com.thiagoti.messageapi.scheduler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.thiagoti.messageapi.bot.client.WhatsAppClient;
import com.thiagoti.messageapi.model.ScheduledMessage;
import com.thiagoti.messageapi.model.ScheduledMessageStatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduledMessageChecker {

    private final ScheduledMessageRepository repository;

    private final WhatsAppClient client;

    @Scheduled(cron = "*/60 * * * * *")
    public void checkScheduledMessage() {
        log.debug("checking scheduled message...");
        if (client.isLoggedIn()) {
            List<ScheduledMessage> list = repository
                    .findByStatusAndAppointmentDateTimeBeforeOrderByAppointmentDateTimeDesc(
                            ScheduledMessageStatus.PENDING, LocalDateTime.now());
            log.debug("list scheduled message size: {}", list.size());
            for (ScheduledMessage scheduledMessage : list) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    log.debug("sending {}", scheduledMessage.getId());
                    client.sendText(scheduledMessage.getTo(), scheduledMessage.getMessage().getContent());
                    scheduledMessage.changeToStatusSent();
                    repository.save(scheduledMessage);
                } catch (Exception e) {
                    log.error("error when try to send message.", e);
                }
            }
        } else {
            log.debug("not loggedin");
        }
    }

}
