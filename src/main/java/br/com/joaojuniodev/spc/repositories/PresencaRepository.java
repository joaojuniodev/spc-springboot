package br.com.joaojuniodev.spc.repositories;

import br.com.joaojuniodev.spc.models.Presenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresencaRepository extends JpaRepository<Presenca, Long> {

    boolean existsByMissaIdAndCatequizandoId(Long missaId, Long catequizandoId);

    @Query("SELECT p FROM Presenca p WHERE p.catequizando.id = :catechumentId")
    List<Presenca> findByCatechumenId(@Param("catechumentId") Long catechumentId);
}
