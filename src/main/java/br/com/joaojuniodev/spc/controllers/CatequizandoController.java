package br.com.joaojuniodev.spc.controllers;

import br.com.joaojuniodev.spc.data.dtos.request.CatequizandoRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.response.catechumens.CatechumenResponseDTO;
import br.com.joaojuniodev.spc.models.enums.EtapaEnum;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import br.com.joaojuniodev.spc.services.CatequizandoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catequizandos/v1")
public class CatequizandoController {

    @Autowired
    private CatequizandoService service;

    @GetMapping(
        produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<List<CatechumenResponseDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(
        value = "/{id}",
        produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<CatechumenResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping(
        value = "/find-by/communityOrParish",
        produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<List<CatechumenResponseDTO>> findByNameCommunityOrParish(
        @RequestParam NameOfTheCommunityOrParishEnum name
    ) {
        return ResponseEntity.ok().body(service.findByNameOfCommunityOrParish(name));
    }

    @GetMapping(
        value = "/find-by/etapaId",
        produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<List<CatechumenResponseDTO>> findByEtapaId(@RequestParam Long id) {
        return ResponseEntity.ok().body(service.findByEtapaId(id));
    }

    @GetMapping(
        value = "/search-by",
        produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<List<CatechumenResponseDTO>> search(@RequestParam String name) {
        return ResponseEntity.ok().body(service.search(name));
    }

    @GetMapping(
        value = "/filter",
        produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<List<CatechumenResponseDTO>> filterByCatechistNameAndStep(
        @RequestParam String catechistName, @RequestParam EtapaEnum step
    ) {
        return ResponseEntity.ok().body(service.findByCatechistAndStep(catechistName, step));
    }

    @PostMapping(
        produces = { MediaType.APPLICATION_JSON_VALUE },
        consumes = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<CatechumenResponseDTO> create(@RequestBody CatequizandoRequestDTO catequizando) {
        return ResponseEntity.ok().body(service.create(catequizando));
    }

    @PutMapping(
        produces = { MediaType.APPLICATION_JSON_VALUE },
        consumes = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<CatechumenResponseDTO> update(@RequestBody CatequizandoRequestDTO catequizando) {
        return ResponseEntity.ok().body(service.update(catequizando));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CatechumenResponseDTO> update(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
