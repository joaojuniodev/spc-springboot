package br.com.joaojuniodev.spc.repositories.specs;

import br.com.joaojuniodev.spc.models.Step;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import org.springframework.data.jpa.domain.Specification;

public class StepSpecification {

    private Specification<Step> spec;

    public StepSpecification() {
        spec = Specification.where(null);
    }

    public Specification<Step> apply() {
        return this.spec;
    }

    public void addToSpecifications(NameOfTheCommunityOrParishEnum communityOrParish) {
        if (communityOrParish != null) hasCommunityOrParish(communityOrParish);
    }

    private void hasCommunityOrParish(NameOfTheCommunityOrParishEnum communityOrParish) {
        this.spec = this.spec.and((root, query, cb) ->
            cb.equal(root.get("nameCommunityOrParish"), communityOrParish));
    }
}
