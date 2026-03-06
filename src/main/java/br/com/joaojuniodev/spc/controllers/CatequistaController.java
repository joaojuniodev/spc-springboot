package br.com.joaojuniodev.spc.controllers;

import br.com.joaojuniodev.spc.data.dtos.request.CatequistaRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.response.CatequistaResponseDTO;
import br.com.joaojuniodev.spc.services.CatequistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catequista/v1")
public class CatequistaController {

    @Autowired
    private CatequistaService service;

    @GetMapping(
        produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<List<CatequistaResponseDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(
        value = "/{id}",
        produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<CatequistaResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping(
        produces = { MediaType.APPLICATION_JSON_VALUE },
        consumes = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<CatequistaResponseDTO> create(@RequestBody CatequistaRequestDTO catequista) {
        return ResponseEntity.ok().body(service.create(catequista));
    }

    @PutMapping(
        produces = { MediaType.APPLICATION_JSON_VALUE },
        consumes = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<CatequistaResponseDTO> update(@RequestBody CatequistaRequestDTO catequista) {
        return ResponseEntity.ok().body(service.update(catequista));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CatequistaResponseDTO> update(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
