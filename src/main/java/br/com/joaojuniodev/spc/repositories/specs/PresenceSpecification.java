package br.com.joaojuniodev.spc.repositories.specs;

import br.com.joaojuniodev.spc.models.Presence;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class PresenceSpecification {

    private Specification<Presence> spec;

    public PresenceSpecification() {
        spec = Specification.where(null);
    }

    public Specification<Presence> apply() {
        return this.spec;
    }

    public void addToSpecifications(Long catechumenId, Long massId, String titleMass, String fullName) {
        if (catechumenId != null) hasCatechumen(catechumenId);
        if (massId != null) hasMassId(massId);
        if (titleMass != null) hasTitleMass(titleMass);
        if (fullName != null) hasFullNameCatechumen(fullName);
    }

    private void hasCatechumen(Long catechumenId) {
        this.spec = this.spec.and((root, query, cb) ->
            cb.equal(root.get("catechumen").get("id"), catechumenId));
    }

    private void hasMassId(Long massId) {
        this.spec = this.spec.and((root, query, cb) ->
            cb.equal(root.get("mass").get("id"), massId));
    }

    private void hasTitleMass(String titleMass) {
        this.spec = this.spec.and((root, query, cb) ->
            cb.equal(root.get("mass").get("title"), titleMass));
    }

    private void hasFullNameCatechumen(String fullName) {
        String[] parts = fullName.toLowerCase().trim().split("\\s+");

        spec = spec.and((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            for (String part : parts) {
                Predicate firstNameLike = cb.like(
                    cb.lower(root.get("catechumen").get("firstName")),
                    "%" + part + "%"
                );

                Predicate lastNameLike = cb.like(
                    cb.lower(root.get("catechumen").get("lastName")),
                    "%" + part + "%"
                );

                predicates.add(cb.or(firstNameLike, lastNameLike));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
}