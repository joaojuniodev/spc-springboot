package br.com.joaojuniodev.spc.repositories;

import br.com.joaojuniodev.spc.models.Catequista;
import br.com.joaojuniodev.spc.models.Catequizando;
import br.com.joaojuniodev.spc.models.enums.EtapaEnum;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatequizandoRepository extends JpaRepository<Catequizando, Long> {

    Optional<List<Catequizando>> findByEtapaId(Long etapaId);

    @Query("""
        SELECT c FROM Catequizando c
        WHERE c.firstName LIKE CONCAT('%', :firstName, '%')
    """)
    List<Catequizando> searchByFirstName(@Param("firstName") String firstName);

    @Query("""
        SELECT c FROM Catequizando c
        JOIN c.etapa e
        JOIN e.catequistas cat
        WHERE cat.firstName = :catechistName
        AND e.etapa = :step
    """)
    List<Catequizando> findByCatechistNameAndStep(
        @Param("catechistName") String catechistName,
        @Param("step") EtapaEnum step
    );

    @Query("SELECT c FROM Catequizando c WHERE c.nameCommunityOrParish = :nameCommunityOrParish")
    List<Catequizando> findByNameCommunityOrParish(
        @Param("nameCommunityOrParish") NameOfTheCommunityOrParishEnum nameCommunityOrParish
    );
}
