package br.com.joaojuniodev.spc.controllers;

import br.com.joaojuniodev.spc.data.dtos.request.CatechumenRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.response.catechumens.CatechumenResponseDTO;
import br.com.joaojuniodev.spc.models.enums.StepNameEnum;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import br.com.joaojuniodev.spc.services.CatechumenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catechumens/v1")
public class CatechumenController {

    @Autowired
    private CatechumenService service;

    @GetMapping
    public ResponseEntity<List<CatechumenResponseDTO>> getAll(
        @RequestParam(required = false) String fullName,
        @RequestParam(required = false) Long stepId,
        @RequestParam(required = false) Long catechistId,
        @RequestParam(required = false) NameOfTheCommunityOrParishEnum communityOrParish
    ) {
        return ResponseEntity.ok().body(service.filter(fullName, stepId, catechistId, communityOrParish));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatechumenResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getById(id));
    }

    @PostMapping(
        produces = { MediaType.APPLICATION_JSON_VALUE },
        consumes = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<CatechumenResponseDTO> create(@RequestBody CatechumenRequestDTO catechumen) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(catechumen));
    }

    @PutMapping(
        produces = { MediaType.APPLICATION_JSON_VALUE },
        consumes = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<CatechumenResponseDTO> update(@RequestBody CatechumenRequestDTO catechumen) {
        return ResponseEntity.ok().body(service.update(catechumen));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CatechumenResponseDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
