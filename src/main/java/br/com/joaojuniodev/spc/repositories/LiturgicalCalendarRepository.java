package br.com.joaojuniodev.spc.repositories;

import br.com.joaojuniodev.spc.models.LiturgicalCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface LiturgicalCalendarRepository extends JpaRepository<LiturgicalCalendar, Long> {

    @Query("SELECT l FROM LiturgicalCalendar l WHERE l.title = :title")
    LiturgicalCalendar findDateByTitle(@Param("title") String title);
}