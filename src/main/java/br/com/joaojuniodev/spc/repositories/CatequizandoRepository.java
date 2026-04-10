package br.com.joaojuniodev.spc.repositories;

import br.com.joaojuniodev.spc.models.Catechumen;
import br.com.joaojuniodev.spc.models.enums.EtapaEnum;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatequizandoRepository extends JpaRepository<Catechumen, Long> {

    Optional<List<Catechumen>> findByEtapaId(Long etapaId);

    @Query("""
        SELECT c
        FROM
            Catechumen c
        WHERE
            c.firstName LIKE CONCAT('%', :firstName, '%')
        AND
            c.lastName LIKE CONCAT('%', :lastName, '%')
    """)
    List<Catechumen> search(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query("""
        SELECT c FROM Catechumen c
        JOIN c.etapa e
        JOIN e.catequistas cat
        WHERE cat.firstName = :catechistName
        AND e.etapa = :step
    """)
    List<Catechumen> findByCatechistNameAndStep(
        @Param("catechistName") String catechistName,
        @Param("step") EtapaEnum step
    );

    @Query("SELECT c FROM Catechumen c WHERE c.nameCommunityOrParish = :nameCommunityOrParish")
    List<Catechumen> findByNameCommunityOrParish(
        @Param("nameCommunityOrParish") NameOfTheCommunityOrParishEnum nameCommunityOrParish
    );

    @Query("SELECT c FROM Catechumen c WHERE c.etapa.id = :etapaId")
    List<Catechumen> findByEtapaIdAndNameCommunityOrParish(@Param("etapaId") Long etapaId);
}
