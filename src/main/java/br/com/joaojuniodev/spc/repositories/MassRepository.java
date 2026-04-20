package br.com.joaojuniodev.spc.repositories;

import br.com.joaojuniodev.spc.models.Mass;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MassRepository extends JpaRepository<Mass, Long>, JpaSpecificationExecutor<Mass> {

    List<Mass> findAll(Specification<Mass> spec);

    @Query("SELECT m.dateTime FROM Mass m WHERE m.nameCommunityOrParish = :nameCommunityOrParish")
    List<LocalDateTime> findAllMassesDates(
        @Param("nameCommunityOrParish") NameOfTheCommunityOrParishEnum nameCommunityOrParish
    );
}
