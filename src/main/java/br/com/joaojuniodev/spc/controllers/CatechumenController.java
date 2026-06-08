package br.com.joaojuniodev.spc.controllers;

import br.com.joaojuniodev.spc.data.dtos.request.catechumen.CatechumenRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.request.catechumen.ParamsAPI;
import br.com.joaojuniodev.spc.data.dtos.response.catechumen.CatechumenDashboardResponseDTO;
import br.com.joaojuniodev.spc.data.dtos.response.catechumen.CatechumenResponseDTO;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import br.com.joaojuniodev.spc.services.CatechumenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/catechumens/v1")
public class CatechumenController {

    @Autowired
    private CatechumenService service;

    @GetMapping
    // apenas admin e coordenador podem listar TODOS os catequizandos
    public ResponseEntity<PagedModel<EntityModel<CatechumenResponseDTO>>> getAll(
        @RequestParam(required = false, value = "page", defaultValue = "0") Integer page,
        @RequestParam(required = false, value = "size", defaultValue = "20") Integer size,
        @RequestParam(required = false, value = "direction", defaultValue = "asc") String direction,

        @RequestParam(required = false) String fullName,
        @RequestParam(required = false) Long stepId,
        @RequestParam(required = false) Long catechistId,
        @RequestParam(required = false) NameOfTheCommunityOrParishEnum communityOrParish
    ) {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "firstName"));
        ParamsAPI params = new ParamsAPI(fullName, stepId, catechistId, communityOrParish);
        return ResponseEntity.ok().body(service.filter(params, pageable));
    }

    @GetMapping("/dashboard")
    public ResponseEntity<CatechumenDashboardResponseDTO> retrieveDashboard() {
        return ResponseEntity.ok().body(service.retrieveDashboard());
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
