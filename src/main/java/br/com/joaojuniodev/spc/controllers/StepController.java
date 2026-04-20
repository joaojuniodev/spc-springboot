package br.com.joaojuniodev.spc.controllers;

import br.com.joaojuniodev.spc.data.dtos.request.StepRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.response.step.StepResponseDTO;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import br.com.joaojuniodev.spc.services.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/steps/v1")
public class StepController {

    @Autowired
    private StepService service;

    @GetMapping
    public ResponseEntity<List<StepResponseDTO>> getAll(
        @RequestParam(required = false) NameOfTheCommunityOrParishEnum communityOrParish
    ) {
        return ResponseEntity.ok().body(service.filter(communityOrParish));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StepResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getById(id));
    }

    @PostMapping(
        produces = { MediaType.APPLICATION_JSON_VALUE },
        consumes = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<StepResponseDTO> create(@RequestBody StepRequestDTO step) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(step));
    }

    @PutMapping(
        produces = { MediaType.APPLICATION_JSON_VALUE },
        consumes = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<StepResponseDTO> update(@RequestBody StepRequestDTO step) {
        return ResponseEntity.ok().body(service.update(step));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StepResponseDTO> update(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
