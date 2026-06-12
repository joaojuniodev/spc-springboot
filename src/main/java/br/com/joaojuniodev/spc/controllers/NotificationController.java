package br.com.joaojuniodev.spc.controllers;

import br.com.joaojuniodev.spc.models.Notification;
import br.com.joaojuniodev.spc.services.NotificationService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications/v1")
public class NotificationController {

    @Autowired
    private NotificationService service;

    @GetMapping
    public ResponseEntity<List<Notification>> getHistory(@AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(service.getHistory(user.getUsername()));
    }

    @GetMapping("/unread-count")
    public ResponseEntity<Long> countUnRead(@AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(service.countUnread(user.getUsername()));
    }

    @PatchMapping("/{id}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable Long id) {
        service.markAsRead(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/read-all")
    public ResponseEntity<Void> markAllAsRead(@AuthenticationPrincipal UserDetails user) {
        service.markAllAsRead(user.getUsername());
        return ResponseEntity.noContent().build();
    }
}
