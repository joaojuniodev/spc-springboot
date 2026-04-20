package br.com.joaojuniodev.spc.repositories.specs;

import br.com.joaojuniodev.spc.models.Catechumen;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CatechumenSpecification {

    private Specification<Catechumen> spec;

    public CatechumenSpecification() {
        spec = Specification.where(null);
    }

    public void addToSpecifications(String fullName, Long stepId, Long catechistId, NameOfTheCommunityOrParishEnum communityOrParish) {
        if (fullName != null) hasFullName(fullName);
        if (stepId != null) hasStep(stepId);
        if (catechistId != null) hasCatechist(catechistId);
        if (communityOrParish != null) hasCommunityOrParish(communityOrParish);
    }

    public Specification<Catechumen> apply() {
        return this.spec;
    }

    private void hasFullName(String fullName) {
        String[] parts = fullName.toLowerCase().trim().split("\\s+");

        spec = spec.and((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            for (String part : parts) {
                Predicate firstNameLike = cb.like(
                    cb.lower(root.get("firstName")),
                    "%" + part + "%"
                );

                Predicate lastNameLike = cb.like(
                    cb.lower(root.get("lastName")),
                    "%" + part + "%"
                );

                predicates.add(cb.or(firstNameLike, lastNameLike));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }

    private void hasStep(Long stepId) {
        spec = spec.and((root, query, cb) ->
            cb.equal(root.get("step").get("id"), stepId));
    }

    private void hasCatechist(Long catechistId) {
        spec = spec.and((root, query, cb) -> {
            Join<Object, Object> step = root.join("step");
            Join<Object, Object> catechists = step.join("catechists");

            return cb.equal(catechists.get("id"), catechistId);
        });
    }

    private void hasCommunityOrParish(NameOfTheCommunityOrParishEnum communityOrParish) {
        spec = spec.and((root, query, cb) ->
            cb.equal(root.get("nameCommunityOrParish"), communityOrParish));
    }
}
