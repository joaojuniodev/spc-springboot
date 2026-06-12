package br.com.joaojuniodev.spc.services;

import br.com.joaojuniodev.spc.data.dtos.response.notification.NotificationDTO;
import br.com.joaojuniodev.spc.models.Notification;
import br.com.joaojuniodev.spc.models.enums.NotificationReferenceType;
import br.com.joaojuniodev.spc.models.enums.NotificationType;
import br.com.joaojuniodev.spc.repositories.NotificationRepository;
import br.com.joaojuniodev.spc.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final Logger logger = LoggerFactory.getLogger(NotificationService.class.getName());

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private NotificationRepository repository;

    @Autowired
    private UserRepository userRepository;

    // ── Métodos de envio base ────────────────────────────────────────────────

    // Entrega privada: só o usuário com esse username recebe
    public void notifyUser(String username, NotificationDTO dto) {
        logger.info("Sending private notification to user: {}", username);

        Notification saved = persist(username, dto);
        NotificationDTO wsDto = toDTO(saved);

        // Spring resolve internamente para /user/{username}/queue/notifications
        messagingTemplate.convertAndSendToUser(
            username,
            "/queue/notifications",
            wsDto
        );
    }

    // Broadcast para todos os coordenadores conectados
    public void notifyCoordinators(NotificationDTO dto) {
        logger.info("Broadcasting notification to coordinators");

        userRepository
            .findAllByRoleName("ROLE_COORDINATOR")
            .forEach(coordinator -> {
                Notification saved = persist(coordinator.getUsername(), dto);
                NotificationDTO wsDto = toDTO(saved);

                messagingTemplate.convertAndSendToUser(
                    coordinator.getUsername(),
                    "/queue/notifications",
                    wsDto
                );
            });
    }

    // Broadcast para todos os admins conectados
    public void notifyAdmins(NotificationDTO dto) {
        logger.info("Broadcasting notification to admins");

        userRepository
            .findAllByRoleName("ROLE_ADMIN")
            .forEach(coordinator -> {
                Notification saved = persist(coordinator.getUsername(), dto);
                NotificationDTO wsDto = toDTO(saved);

                messagingTemplate.convertAndSendToUser(
                    coordinator.getUsername(),
                    "/queue/notifications",
                    wsDto
                );
            });
    }

    // ── Casos de negócio do SPC ──────────────────────────────────────────────

    public void notifyPresenceRegistered(
        String catechistUsername,
        String catechistFullName,
        int totalRegistered,
        Long massId
    ) {
        // 1. Confirma para o próprio catequista
        notifyUser(catechistUsername, new NotificationDTO(
            "SUCCESS",
            "Presença registrada",
            "Você registrou presença para " + totalRegistered + " catequizandos com sucesso"
        ));

        // 2. Alerta para coordenadores e admins (broadcast)
        var alertDTO = new NotificationDTO(
            "INFO",
            "Presença registrada",
            catechistFullName + " registrou presença para " + totalRegistered + " catequizandos",
            massId,
            NotificationReferenceType.MASS.name()
        );
        notifyCoordinators(alertDTO);
        notifyAdmins(alertDTO);
    }

    public void notifyPresenceRetroactiveRegistered(
        String catechistUsername,
        String catechistFullName,
        String catechumenFullName,
        String massTitle,
        Long massId
    ) {
        notifyUser(catechistUsername, new NotificationDTO(
            "SUCCESS",
            "Presença registrada com sucesso",
            "Você registrou presença para " + catechumenFullName + " na missa " + massTitle + " com sucesso"
        ));

        var alertDTO = new NotificationDTO(
            "INFO",
            "Presença registrada",
            catechistFullName + " registrou presença para " + catechumenFullName + " na missa " + massTitle,
            massId,
            NotificationReferenceType.MASS.name()
        );
        notifyCoordinators(alertDTO);
        notifyAdmins(alertDTO);
    }

    // ── Métodos de leitura (usados pelo controller REST)

    public List<Notification> getHistory(String username) {
        logger.info("Fetching notification history for user: {}", username);
        return repository.findByUsernameOrderByCreatedAtDesc(username);
    }

    public long countUnread(String username) {
        return repository.countByUsernameAndIsReadFalse(username);
    }

    public void markAsRead(Long notificationId) {
        logger.info("Marking notification {} as read", notificationId);
        repository.markAsRead(notificationId);
    }

    public void markAllAsRead(String username) {
        logger.info("Marking all notifications as read for user: {}", username);
        repository.markAllAsRead(username);
    }

    // ── Interno ──────────────────────────────────────────────────────────────

    private Notification persist(String username, NotificationDTO dto) {
        Notification entity = new Notification(
            username,
            NotificationType.valueOf(dto.type()),
            dto.title(),
            dto.body(),
            false,
            dto.referenceId(),
            dto.referenceType() != null
                ? NotificationReferenceType.valueOf(dto.referenceType())
                : null
        );
        return this.repository.save(entity);
    }

    private NotificationDTO toDTO(Notification entity) {
        return new NotificationDTO(
            entity.getId(),
            entity.getType().name(),
            entity.getTitle(),
            entity.getBody(),
            entity.getReferenceId(),
            entity.getReferenceType() != null
                ? entity.getReferenceType().name()
                : null,
            entity.getIsRead(),
            entity.getCreatedAt()
        );
    }
}