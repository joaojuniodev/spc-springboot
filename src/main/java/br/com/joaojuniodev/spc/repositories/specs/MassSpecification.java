package br.com.joaojuniodev.spc.repositories.specs;

import br.com.joaojuniodev.spc.models.Mass;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class MassSpecification {

    private Specification<Mass> spec;

    public MassSpecification() {
        spec = Specification.where(null);
    }

    public void addToSpecifications(NameOfTheCommunityOrParishEnum communityOrParish, LocalDateTime occurredUntil) {
        if (communityOrParish != null) hasCommunityOrParish(communityOrParish);
        if (occurredUntil != null) hasOccurredUntil(occurredUntil);
    }

    public Specification<Mass> apply() {
        return this.spec;
    }

    private void hasCommunityOrParish(NameOfTheCommunityOrParishEnum communityOrParish) {
        spec = spec.and((root, query, cb) ->
            cb.equal(root.get("nameCommunityOrParish"), communityOrParish));
    }

    private void hasOccurredUntil(LocalDateTime occurredUntil) {
        spec = spec.and((root, query, cb) -> {
            LocalDateTime startDate = LocalDateTime.of(2026,1,1,0,0);
            return cb.between(root.get("dateTime"), startDate, occurredUntil);
        });
    }
}
