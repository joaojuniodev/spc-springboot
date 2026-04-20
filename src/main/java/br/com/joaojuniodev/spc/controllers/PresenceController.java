package br.com.joaojuniodev.spc.controllers;

import br.com.joaojuniodev.spc.data.dtos.request.PresenceRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.response.presence.CatechumenIsPresentDTO;
import br.com.joaojuniodev.spc.data.dtos.response.presence.PresenceResponseDTO;
import br.com.joaojuniodev.spc.services.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/presences/v1")
public class PresenceController {

    @Autowired
    private PresenceService service;

    @GetMapping
    public ResponseEntity<List<PresenceResponseDTO>> getAll(
        @RequestParam(required = false) Long catechumenId,
        @RequestParam(required = false) String titleMass
    ) {
        return ResponseEntity.ok().body(service.filter(catechumenId, titleMass));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PresenceResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getById(id));
    }

    @PostMapping(
        produces = { MediaType.APPLICATION_JSON_VALUE },
        consumes = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<PresenceResponseDTO> create(@RequestBody PresenceRequestDTO presence) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(presence));
    }

    @PutMapping(
        produces = { MediaType.APPLICATION_JSON_VALUE },
        consumes = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<PresenceResponseDTO> update(@RequestBody PresenceRequestDTO presence) {
        return ResponseEntity.ok().body(service.update(presence));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
