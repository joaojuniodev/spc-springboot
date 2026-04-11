package br.com.joaojuniodev.spc.controllers;

import br.com.joaojuniodev.spc.data.dtos.request.PresencaRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.response.catechumens.CatechumenResponseByPresenceDTO;
import br.com.joaojuniodev.spc.data.dtos.response.catechumens.CatechumenResponseDTO;
import br.com.joaojuniodev.spc.data.dtos.response.presence.CatechumenIsPresentDTO;
import br.com.joaojuniodev.spc.data.dtos.response.presence.PresencaResponseDTO;
import br.com.joaojuniodev.spc.services.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/presencas/v1")
public class PresenceController {

    @Autowired
    private PresenceService service;

    @GetMapping(
        produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<List<PresencaResponseDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(
        value = "/{id}",
        produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<PresencaResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping(
        value = "/findByCatechumenId/{catechumenId}",
        produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<List<PresencaResponseDTO>> findByCatechumen(@PathVariable Long catechumenId) {
        return ResponseEntity.ok().body(service.findByCatechumenId(catechumenId));
    }

    @GetMapping(
        value = "present-catechumens/mass",
        produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<List<CatechumenIsPresentDTO>> getPresentCatechumensByMass(@RequestParam("title") String titleMassFromLiturgicalCalendar) {
        return ResponseEntity.ok().body(service.listCatechumensPresentAtMass(titleMassFromLiturgicalCalendar));
    }

    @PostMapping(
        produces = { MediaType.APPLICATION_JSON_VALUE },
        consumes = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<PresencaResponseDTO> create(@RequestBody PresencaRequestDTO presenca) {
        return ResponseEntity.ok().body(service.create(presenca));
    }

    @PutMapping(
        produces = { MediaType.APPLICATION_JSON_VALUE },
        consumes = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<PresencaResponseDTO> update(@RequestBody PresencaRequestDTO presenca) {
        return ResponseEntity.ok().body(service.update(presenca));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
