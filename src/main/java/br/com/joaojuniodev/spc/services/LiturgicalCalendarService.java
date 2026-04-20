package br.com.joaojuniodev.spc.services;

import br.com.joaojuniodev.spc.data.dtos.response.liturgicalCalendar.LiturgicalCalendarResponseDTO;
import br.com.joaojuniodev.spc.mapper.ObjectMapperManually;
import br.com.joaojuniodev.spc.models.LiturgicalCalendar;
import br.com.joaojuniodev.spc.repositories.LiturgicalCalendarRepository;
import br.com.joaojuniodev.spc.repositories.specs.LiturgicalCalendarSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiturgicalCalendarService {

    private final Logger logger = LoggerFactory.getLogger(LiturgicalCalendarService.class.getName());

    @Autowired
    private LiturgicalCalendarRepository repository;

    @Autowired
    private ObjectMapperManually mapper;

    public List<LiturgicalCalendarResponseDTO> filter(String title) {

        logger.info("Filtering Liturgical Calendars");

        LiturgicalCalendarSpecification spec = new LiturgicalCalendarSpecification();
        spec.addToSpecifications(title);

        return repository
            .findAll(spec.apply())
            .stream()
            .map(entity -> mapper.convertLiturgicalCalendarEntityToResponseDTO(entity))
            .toList();
    }
}
