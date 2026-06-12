package br.com.joaojuniodev.spc.data.dtos.response.notification;

import java.time.LocalDateTime;

public record NotificationDTO(
    Long id,
    String type,
    String title,
    String body,
    Long referenceId,
    String referenceType,
    Boolean isRead,
    LocalDateTime createdAt
) {

    // Construtor para conveniência para notificações sem referência
    public NotificationDTO(String type, String title, String body) {
        this(null, type, title, body, null, null, false, LocalDateTime.now());
    }

    // Construtor para conveniência para notificações sem referência
    public NotificationDTO(String type, String title, String body, Long referenceId, String referenceType) {
        this(null, type, title, body, referenceId, referenceType, false, LocalDateTime.now());
    }
}
