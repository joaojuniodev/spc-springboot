package br.com.joaojuniodev.spc.controllers;

import br.com.joaojuniodev.spc.data.dtos.request.MassRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.response.mass.MassResponseDTO;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import br.com.joaojuniodev.spc.services.MassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/masses/v1")
public class MassController {

    @Autowired
    private MassService service;

    @GetMapping
    public ResponseEntity<List<MassResponseDTO>> getAll(
        @RequestParam(required = false) NameOfTheCommunityOrParishEnum communityOrParish,

        @RequestParam(required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime occurredUntil
    ) {
        return ResponseEntity.ok().body(service.filter(communityOrParish, occurredUntil));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MassResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getById(id));
    }

    @GetMapping("/massesDates")
    public ResponseEntity<List<LocalDateTime>> getMassesDatesByCommunityOrParish(
        @RequestParam NameOfTheCommunityOrParishEnum communityOrParish
    ) {
        return ResponseEntity.ok().body(service.getMassesDatesByCommunityOrParish(communityOrParish));
    }

    @PostMapping(
        produces = { MediaType.APPLICATION_JSON_VALUE },
        consumes = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<MassResponseDTO> create(@RequestBody MassRequestDTO missa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(missa));
    }

    @PutMapping(
        produces = { MediaType.APPLICATION_JSON_VALUE },
        consumes = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<MassResponseDTO> update(@RequestBody MassRequestDTO missa) {
        return ResponseEntity.ok().body(service.update(missa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}