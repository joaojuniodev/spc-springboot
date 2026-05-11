package br.com.joaojuniodev.spc.repositories;

import br.com.joaojuniodev.spc.models.Presence;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresenceRepository extends JpaRepository<Presence, Long>, JpaSpecificationExecutor<Presence> {

    List<Presence> findAll(Specification<Presence> spec);

    boolean existsByMassIdAndCatechumenId(Long massId, Long catechumenId);

    boolean existsByCatechistId(Long catechistId);

    boolean existsByMassId(Long massId);
}
