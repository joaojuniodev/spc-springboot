package br.com.joaojuniodev.spc.controllers;

import br.com.joaojuniodev.spc.data.dtos.request.MissaRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.response.EtapaResponseDTO;
import br.com.joaojuniodev.spc.data.dtos.response.MissaResponseDTO;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import br.com.joaojuniodev.spc.services.MissaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/missas/v1")
public class MissaController {

    @Autowired
    private MissaService service;

    @GetMapping(
        produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<List<MissaResponseDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(
        value = "/{id}",
        produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<MissaResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping(
        value = "/findByOccurredToThisToday",
        produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<List<MissaResponseDTO>> findByOccurredToThisToday() {
        return ResponseEntity.ok().body(service.findByOccurredToThisToday());
    }

    @GetMapping(
        value = "/find-by",
        produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<List<MissaResponseDTO>> findByNameCommunityOrParish(
        @RequestParam NameOfTheCommunityOrParishEnum communityOrParish
    ) {
        return ResponseEntity.ok().body(service.findByNameOfCommunityOrParish(communityOrParish));
    }

    @GetMapping(
        value = "/find-by/masses-dates",
        produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<List<LocalDateTime>> findAllMassesDatesByCommunityOrParish(
        @RequestParam NameOfTheCommunityOrParishEnum communityOrParish
    ) {
        return ResponseEntity.ok().body(service.findAllMassesDates(communityOrParish));
    }

    @PostMapping(
        produces = { MediaType.APPLICATION_JSON_VALUE },
        consumes = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<MissaResponseDTO> create(@RequestBody MissaRequestDTO missa) {
        return ResponseEntity.ok().body(service.create(missa));
    }

    @PutMapping(
        produces = { MediaType.APPLICATION_JSON_VALUE },
        consumes = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<MissaResponseDTO> update(@RequestBody MissaRequestDTO missa) {
        return ResponseEntity.ok().body(service.update(missa));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}