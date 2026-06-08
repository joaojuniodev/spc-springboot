package br.com.joaojuniodev.spc.repositories.specs;

import br.com.joaojuniodev.spc.models.Mass;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class MassSpecification {

    private Specification<Mass> spec;

    public MassSpecification() {
        spec = Specification.where(null);
    }

    public void addToSpecifications(NameOfTheCommunityOrParishEnum communityOrParish, String title, LocalDateTime occurredUntil, LocalDateTime occurredUntilByMassTitle) {
        if (communityOrParish != null) hasCommunityOrParish(communityOrParish);
        if (title != null) hasTitle(title);
        if (occurredUntil != null) hasOccurredUntil(occurredUntil);
        if (occurredUntilByMassTitle != null) hasOccurredUntilByMassTitle(occurredUntilByMassTitle);
    }

    public Specification<Mass> apply() {
        return this.spec;
    }

    private void hasCommunityOrParish(NameOfTheCommunityOrParishEnum communityOrParish) {
        this.spec = this.spec.and((root, query, cb) ->
            cb.equal(root.get("nameCommunityOrParish"), communityOrParish));
    }

    private void hasTitle(String title) {
        this.spec = this.spec.and((root, query, cb) -> cb.equal(root.get("title"), title));
    }

    private void hasOccurredUntil(LocalDateTime occurredUntil) {
        this.spec = this.spec.and((root, query, cb) -> {
            LocalDateTime startDate = LocalDateTime.of(2026,1,1,0,0);
            return cb.between(root.get("dateTime"), startDate, occurredUntil);
        });
    }

    private void hasOccurredUntilByMassTitle(LocalDateTime occurredUntil) {
        this.spec = this.spec.and((root, query, cb) -> {

            // Subquery 1: SELECT MAX(m2.date_time) FROM masses m2 WHERE m2.title = m.title
            Subquery<LocalDateTime> maxDateSubquery = query.subquery(LocalDateTime.class);
            Root<Mass> maxDateRoot = maxDateSubquery.from(Mass.class);
            maxDateSubquery.select(cb.greatest(maxDateRoot.<LocalDateTime>get("dateTime")))
                .where(cb.equal(maxDateRoot.get("title"), root.get("title")));

            // Subquery 2: SELECT MIN(m3.id) FROM masses m3 WHERE m3.title = m.title AND m3.date_time = m.date_time
            Subquery<Long> minIdSubquery = query.subquery(Long.class);
            Root<Mass> minIdRoot = minIdSubquery.from(Mass.class);
            minIdSubquery.select(cb.least(minIdRoot.<Long>get("id")))
                .where(cb.and(
                    cb.equal(minIdRoot.get("title"), root.get("title")),
                    cb.equal(minIdRoot.get("dateTime"), root.get("dateTime"))
                ));

            // WHERE date_time < occurredUntil AND date_time = (subquery1) AND id = (subquery2)
            return cb.and(
                cb.lessThan(root.get("dateTime"), occurredUntil),
                cb.equal(root.get("dateTime"), maxDateSubquery),
                cb.equal(root.get("id"), minIdSubquery)
            );
        });
    }
}
