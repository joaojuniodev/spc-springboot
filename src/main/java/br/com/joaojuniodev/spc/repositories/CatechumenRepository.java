package br.com.joaojuniodev.spc.repositories;

import br.com.joaojuniodev.spc.models.Catechumen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatechumenRepository extends JpaRepository<Catechumen, Long>, JpaSpecificationExecutor<Catechumen> {

    Page<Catechumen> findAll(Specification<Catechumen> spec, Pageable pageable);

    Optional<List<Catechumen>> findByStepId(Long etapaId);
}