package br.com.joaojuniodev.spc.repositories.specs;

import br.com.joaojuniodev.spc.models.Catechist;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import jakarta.persistence.criteria.Expression;
import org.springframework.data.jpa.domain.Specification;

public class CatechistSpecification {

    private Specification<Catechist> spec;

    public CatechistSpecification() {
        spec = Specification.where(null);
    }

    public Specification<Catechist> apply() {
        return this.spec;
    }

    public void addToSpecifications(Long stepId, String fullName, NameOfTheCommunityOrParishEnum communityOrParish) {
        if (stepId != null) hasStep(stepId);
        if (fullName != null) hasFullName(fullName);
        if (communityOrParish != null) hasCommunityOrParish(communityOrParish);
    }

    private void hasStep(Long stepId) {
        this.spec = this.spec.and((root, query, cb) ->
                cb.equal(root.get("step").get("id"), stepId));
    }

    private void hasFullName(String fullName) {
        spec = spec.and((root, query, cb) -> {
            Expression<String> catechistFullName = cb.concat(
                cb.concat(root.get("firstName"), " "),
                root.get("lastName")
            );
            return cb.like(cb.lower(catechistFullName), "%" + fullName.toLowerCase() + "%");
        });
    }

    private void hasCommunityOrParish(NameOfTheCommunityOrParishEnum communityOrParish) {
        spec = spec.and((root, query, cb) ->
            cb.equal(root.get("nameCommunityOrParish"), communityOrParish));
    }
}