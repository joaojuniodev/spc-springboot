package br.com.joaojuniodev.spc.repositories;

import br.com.joaojuniodev.spc.models.Catequista;
import br.com.joaojuniodev.spc.models.Missa;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MissaRepository extends JpaRepository<Missa, Long> {

    @Query("""
        SELECT m
        FROM Missa m
        WHERE m.dateTime BETWEEN :startDate AND :today
    """)
    List<Missa> findByMassesOccurredToThisDay(
        @Param("startDate") LocalDateTime startDate,
        @Param("today") LocalDateTime today
    );

    @Query("SELECT m.dateTime FROM Missa m")
    List<LocalDateTime> findAllMassesDates();

    @Query("SELECT m FROM Missa m WHERE m.nameCommunityOrParish = :nameCommunityOrParish")
    List<Missa> findByNameCommunityOrParish(
        @Param("nameCommunityOrParish") NameOfTheCommunityOrParishEnum nameCommunityOrParish
    );
}
