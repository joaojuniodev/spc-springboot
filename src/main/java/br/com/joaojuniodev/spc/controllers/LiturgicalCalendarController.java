package br.com.joaojuniodev.spc.controllers;

import br.com.joaojuniodev.spc.data.dtos.response.LiturgicalCalendarResponseDTO;
import br.com.joaojuniodev.spc.models.LiturgicalCalendar;
import br.com.joaojuniodev.spc.services.LiturgicalCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/liturgicalCalendar/v1")
public class LiturgicalCalendarController {

    @Autowired
    private LiturgicalCalendarService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LiturgicalCalendarResponseDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(
        value = "/find-by",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LiturgicalCalendarResponseDTO> findByTitle(@RequestParam String title) {
        return ResponseEntity.ok().body(service.findByTitle(title));
    }
}
