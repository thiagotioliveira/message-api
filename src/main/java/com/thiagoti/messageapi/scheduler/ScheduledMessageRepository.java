package com.thiagoti.messageapi.scheduler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thiagoti.messageapi.model.ScheduledMessage;
import com.thiagoti.messageapi.model.ScheduledMessageStatus;

public interface ScheduledMessageRepository extends JpaRepository<ScheduledMessage, UUID> {

    List<ScheduledMessage> findByStatusAndAppointmentDateTimeBeforeOrderByAppointmentDateTimeDesc(ScheduledMessageStatus status, LocalDateTime appointmentDateTime);
    
}
