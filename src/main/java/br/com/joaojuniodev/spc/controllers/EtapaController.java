package br.com.joaojuniodev.spc.controllers;

import br.com.joaojuniodev.spc.data.dtos.request.EtapaRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.response.step.EtapaResponseDTO;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import br.com.joaojuniodev.spc.services.EtapaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etapas/v1")
public class EtapaController {

    @Autowired
    private EtapaService service;

    @GetMapping(
        produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<List<EtapaResponseDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(
        value = "/{id}",
        produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<EtapaResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping(
        value = "/find-by",
        produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<List<EtapaResponseDTO>> findByNameCommunityOrParish(
        @RequestParam NameOfTheCommunityOrParishEnum communityOrParish
    ) {
        return ResponseEntity.ok().body(service.findByNameOfCommunityOrParish(communityOrParish));
    }

    @PostMapping(
        produces = { MediaType.APPLICATION_JSON_VALUE },
        consumes = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<EtapaResponseDTO> create(@RequestBody EtapaRequestDTO etapa) {
        return ResponseEntity.ok().body(service.create(etapa));
    }

    @PutMapping(
        produces = { MediaType.APPLICATION_JSON_VALUE },
        consumes = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<EtapaResponseDTO> update(@RequestBody EtapaRequestDTO etapa) {
        return ResponseEntity.ok().body(service.update(etapa));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<EtapaResponseDTO> update(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
