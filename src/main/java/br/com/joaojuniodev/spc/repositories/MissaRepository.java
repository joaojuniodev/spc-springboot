package br.com.joaojuniodev.spc.repositories;

import br.com.joaojuniodev.spc.models.Missa;
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
}
