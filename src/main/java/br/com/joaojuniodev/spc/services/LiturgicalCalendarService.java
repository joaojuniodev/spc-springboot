package br.com.joaojuniodev.spc.services;

import br.com.joaojuniodev.spc.data.dtos.response.LiturgicalCalendarResponseDTO;
import br.com.joaojuniodev.spc.mapper.ObjectMapperManually;
import br.com.joaojuniodev.spc.repositories.LiturgicalCalendarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LiturgicalCalendarService {

    private final Logger logger = LoggerFactory.getLogger(LiturgicalCalendarService.class.getName());

    @Autowired
    private LiturgicalCalendarRepository repository;

    @Autowired
    private ObjectMapperManually mapper;

    public List<LiturgicalCalendarResponseDTO> findAll() {

        logger.info("Finding all Liturgical Calendar");

        return repository.findAll().stream()
            .map(entity -> mapper.convertLiturgicalCalendarEntityToResponseDTO(entity)).toList();
    }

    public LiturgicalCalendarResponseDTO findByTitle(String title) {

        logger.info("Finding by title");

        return mapper.convertLiturgicalCalendarEntityToResponseDTO(repository.findDateByTitle(title));
    }
}
