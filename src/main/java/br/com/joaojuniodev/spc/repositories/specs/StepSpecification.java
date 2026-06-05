package br.com.joaojuniodev.spc.repositories.specs;

import br.com.joaojuniodev.spc.models.Catechist;
import br.com.joaojuniodev.spc.models.Step;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class StepSpecification {

    private Specification<Step> spec;

    public StepSpecification() {
        spec = Specification.where(null);
    }

    public Specification<Step> apply() {
        return this.spec;
    }

    public void addToSpecifications(NameOfTheCommunityOrParishEnum communityOrParish, Long catechistId) {
        if (communityOrParish != null) hasCommunityOrParish(communityOrParish);
        if (catechistId != null) hasCatechist(catechistId);
    }

    private void hasCommunityOrParish(NameOfTheCommunityOrParishEnum communityOrParish) {
        this.spec = this.spec.and((root, query, cb) ->
            cb.equal(root.get("nameCommunityOrParish"), communityOrParish));
    }

    private void hasCatechist(Long catechistId) {
        this.spec = this.spec.and((root, query, cb) -> {
            Join<Step, Catechist> catechistJoin = root.join("catechists");
            return cb.equal(catechistJoin.get("id"), catechistId);
        });
    }
}
