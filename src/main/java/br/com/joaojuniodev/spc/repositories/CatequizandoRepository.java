package br.com.joaojuniodev.spc.repositories;

import br.com.joaojuniodev.spc.models.Catequizando;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatequizandoRepository extends JpaRepository<Catequizando, Long> {

    Optional<List<Catequizando>> findByEtapaId(Long etapaId);
}
