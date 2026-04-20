package br.com.joaojuniodev.spc.repositories;

import br.com.joaojuniodev.spc.models.Catechist;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatechistRepository extends JpaRepository<Catechist, Long>, JpaSpecificationExecutor<Catechist> {

    List<Catechist> findAll(Specification<Catechist> spec);

    @Query("SELECT c FROM Catechist c WHERE c.step.id = :stepId")
    List<Catechist> findByStepId(@Param("stepId") Long stepId);
}