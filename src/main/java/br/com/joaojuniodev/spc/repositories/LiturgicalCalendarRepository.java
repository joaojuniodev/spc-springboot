package br.com.joaojuniodev.spc.repositories;

import br.com.joaojuniodev.spc.models.LiturgicalCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiturgicalCalendarRepository extends JpaRepository<LiturgicalCalendar, Long> {
}