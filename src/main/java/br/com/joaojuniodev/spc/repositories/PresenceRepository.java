package br.com.joaojuniodev.spc.repositories;

import br.com.joaojuniodev.spc.data.dtos.response.presence.PresenceUserSummaryDTO;
import br.com.joaojuniodev.spc.models.Presence;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresenceRepository extends JpaRepository<Presence, Long>, JpaSpecificationExecutor<Presence> {

    List<Presence> findAll(Specification<Presence> spec);

    boolean existsByMassIdAndCatechumenId(Long massId, Long catechumenId);

    boolean existsByUserId(Long userId);

    boolean existsByMassId(Long massId);

    @Query("""
        SELECT new br.com.joaojuniodev.spc.data.dtos.response.presence.PresenceUserSummaryDTO(
            p.user.username,
            p.user.fullName,
            p.mass.id,
            COUNT(p.catechumen.id),
            MAX(p.registeredAt)
        )
        FROM Presence p
        WHERE p.mass.id = :massId
        GROUP BY p.user.username, p.user.fullName, p.mass.id
    """)
    List<PresenceUserSummaryDTO> findUserSummaryByMassId(@Param("massId") Long massId);
}
