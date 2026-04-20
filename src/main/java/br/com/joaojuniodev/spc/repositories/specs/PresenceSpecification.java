package br.com.joaojuniodev.spc.repositories.specs;

import br.com.joaojuniodev.spc.models.Presence;
import org.springframework.data.jpa.domain.Specification;

public class PresenceSpecification {

    private Specification<Presence> spec;

    public PresenceSpecification() {
        spec = Specification.where(null);
    }

    public Specification<Presence> apply() {
        return this.spec;
    }

    public void addToSpecifications(Long catechumenId, String titleMass) {
        if (catechumenId != null) hasCatechumen(catechumenId);
        if (titleMass != null) hasTitleMass(titleMass);
    }

    private void hasCatechumen(Long catechumenId) {
        this.spec = this.spec.and((root, query, cb) ->
            cb.equal(root.get("catechumen").get("id"), catechumenId));
    }

    private void hasTitleMass(String titleMass) {
        this.spec = this.spec.and((root, query, cb) ->
            cb.equal(root.get("mass").get("title"), titleMass));
    }
}