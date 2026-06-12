package br.com.joaojuniodev.spc.repositories;

import br.com.joaojuniodev.spc.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // Histórico completo - carregado ao abrir o sistema
    List<Notification> findByUsernameOrderByCreatedAtDesc(String username);

    // Só as não lidas - para o badge do sino
    long countByUsernameAndIsReadFalse(String username);

    @Modifying
    @Transactional
    @Query("UPDATE Notification n SET n.isRead = true WHERE n.id = :id")
    void markAsRead(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Notification n SET n.isRead = true WHERE n.username = :username")
    void markAllAsRead(@Param("username") String username);
}