package br.com.joaojuniodev.spc.controllers;

import br.com.joaojuniodev.spc.data.dtos.response.liturgicalCalendar.LiturgicalCalendarResponseDTO;
import br.com.joaojuniodev.spc.services.LiturgicalCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/liturgicalCalendars/v1")
public class LiturgicalCalendarController {

    @Autowired
    private LiturgicalCalendarService service;

    @GetMapping
    public ResponseEntity<List<LiturgicalCalendarResponseDTO>> getAll(
        @RequestParam(required = false) String title
    ) {
        return ResponseEntity.ok().body(service.filter(title));
    }
}
