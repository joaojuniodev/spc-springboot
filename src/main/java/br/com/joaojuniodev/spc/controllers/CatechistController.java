package br.com.joaojuniodev.spc.controllers;

import br.com.joaojuniodev.spc.data.dtos.request.CatechistRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.response.catechist.CatechistResponseDTO;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import br.com.joaojuniodev.spc.services.CatechistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catechists/v1")
public class CatechistController {

    @Autowired
    private CatechistService service;

    @GetMapping
    public ResponseEntity<List<CatechistResponseDTO>> getAll(
        @RequestParam(required = false) Long stepId,
        @RequestParam(required = false) String fullName,
        @RequestParam(required = false) NameOfTheCommunityOrParishEnum communityOrParish
    ) {
        return ResponseEntity.ok().body(service.filter(stepId, fullName, communityOrParish));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatechistResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getById(id));
    }

    @PostMapping(
        produces = { MediaType.APPLICATION_JSON_VALUE },
        consumes = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<CatechistResponseDTO> create(@RequestBody CatechistRequestDTO catechist) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(catechist));
    }

    @PutMapping(
        produces = { MediaType.APPLICATION_JSON_VALUE },
        consumes = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<CatechistResponseDTO> update(@RequestBody CatechistRequestDTO catechist) {
        return ResponseEntity.ok().body(service.update(catechist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CatechistResponseDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
