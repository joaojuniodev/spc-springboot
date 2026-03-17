package br.com.joaojuniodev.spc.repositories;

import br.com.joaojuniodev.spc.models.Etapa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EtapaRepository extends JpaRepository<Etapa, Long> {

    @Query("SELECT e FROM Etapa e WHERE e.catequista.id = :catechistId")
    Optional<Etapa> findByCatechistId(@Param("catechistId") Long catechistId);
}
