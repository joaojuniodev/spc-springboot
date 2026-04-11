package br.com.joaojuniodev.spc.repositories;

import br.com.joaojuniodev.spc.models.Catechumen;
import br.com.joaojuniodev.spc.models.Presence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresenceRepository extends JpaRepository<Presence, Long> {

    boolean existsByMissaIdAndCatequizandoId(Long missaId, Long catequizandoId);

    @Query("SELECT p FROM Presenca p WHERE p.catequizando.id = :catechumenId")
    List<Presence> findByCatechumenId(@Param("catechumenId") Long catechumenId);

    @Query(
        value = """
            SELECT c
            FROM Presenca p
            JOIN p.catequizando c
            WHERE p.missa.title = :titleMassFromLiturgicalCalendar
        """
    )
    List<Catechumen> findPresentsCatechumensByMass(@Param("titleMassFromLiturgicalCalendar") String titleMassFromLiturgicalCalendar);
}
