package br.com.joaojuniodev.spc.repositories;

import br.com.joaojuniodev.spc.models.Catequista;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatequistaRepository extends JpaRepository<Catequista, Long> {

    @Query("SELECT c FROM Catequista c WHERE c.nameCommunityOrParish = :nameCommunityOrParish")
    List<Catequista> findByNameCommunityOrParish(
        @Param("nameCommunityOrParish")NameOfTheCommunityOrParishEnum nameCommunityOrParish
    );

    @Query("SELECT c FROM Catequista c WHERE c.etapa.id = :stepId")
    List<Catequista> findByStepId(@Param("stepId") Long stepId);
}