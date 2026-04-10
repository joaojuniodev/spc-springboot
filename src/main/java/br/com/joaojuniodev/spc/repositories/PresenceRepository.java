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

    @Query("SELECT p FROM Presence p WHERE p.catequizando.id = :catechumenId")
    List<Presence> findByCatechumenId(@Param("catechumenId") Long catechumenId);

    @Query(
        value = """
            SELECT c.*
            FROM presenca p
            JOIN catequizando AS c ON c.id = p.catequizando_id
            WHERE p.missa_id = :massId;
        """,
        nativeQuery = true
    )
    List<Catechumen> findPresentsCatechumensByMassId(@Param("massId") Long massId);
}
