package br.com.joaojuniodev.spc.controllers;

import br.com.joaojuniodev.spc.data.dtos.request.PresenceRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.response.presence.PresenceRegisterDTO;
import br.com.joaojuniodev.spc.data.dtos.response.presence.PresenceRegisterRetroactiveDTO;
import br.com.joaojuniodev.spc.data.dtos.response.presence.PresenceResponseDTO;
import br.com.joaojuniodev.spc.data.dtos.response.presence.PresenceUserSummaryDTO;
import br.com.joaojuniodev.spc.services.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/presences/v1")
public class PresenceController {

    @Autowired
    private PresenceService service;

    @GetMapping
    @PreAuthorize("hasAuthority('PRESENCE_HISTORY_READ')")
    public ResponseEntity<List<PresenceResponseDTO>> getAll(
        @RequestParam(required = false) Long catechumenId,
        @RequestParam(required = false) Long massId,
        @RequestParam(required = false) String titleMass,
        @RequestParam(required = false) String fullName
    ) {
        return ResponseEntity.ok().body(service.filter(catechumenId, massId, titleMass, fullName));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PresenceResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<PresenceRegisterDTO> registerPresences(@RequestBody List<PresenceRequestDTO> presences) {
        return ResponseEntity.ok().body(service.registerPresences(presences));
    }

    @PostMapping("/retroactive")
    public ResponseEntity<PresenceRegisterRetroactiveDTO> registerRetroactive(@RequestBody PresenceRequestDTO presence) {
        return ResponseEntity.ok().body(service.registerRetroactive(presence));
    }

    @GetMapping("/summary-by-mass")
    public ResponseEntity<List<PresenceUserSummaryDTO>> getSummaryByMass(@RequestParam Long massId) {
        return ResponseEntity.ok().body(service.getSummaryByMass(massId));
    }

    @PutMapping
    public ResponseEntity<PresenceResponseDTO> update(@RequestBody PresenceRequestDTO presence) {
        return ResponseEntity.ok().body(service.update(presence));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
