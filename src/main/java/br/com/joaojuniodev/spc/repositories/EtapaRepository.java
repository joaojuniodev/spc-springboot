package br.com.joaojuniodev.spc.repositories;

import br.com.joaojuniodev.spc.data.projections.EtapaResumoProjection;
import br.com.joaojuniodev.spc.models.Etapa;
import br.com.joaojuniodev.spc.models.Missa;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtapaRepository extends JpaRepository<Etapa, Long> {

    @Query("SELECT e FROM Etapa e WHERE e.nameCommunityOrParish = :nameCommunityOrParish")
    List<Etapa> findByNameCommunityOrParish(
        @Param("nameCommunityOrParish") NameOfTheCommunityOrParishEnum nameCommunityOrParish
    );
}
