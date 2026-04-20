package br.com.joaojuniodev.spc.repositories.specs;

import br.com.joaojuniodev.spc.models.LiturgicalCalendar;
import org.springframework.data.jpa.domain.Specification;

public class LiturgicalCalendarSpecification {

    Specification<LiturgicalCalendar> spec;

    public LiturgicalCalendarSpecification() {
        spec = Specification.where(null);
    }

    public Specification<LiturgicalCalendar> apply() {
        return this.spec;
    }

    public void addToSpecifications(String title) {
        if (title != null) hasTitle(title);
    }

    private void hasTitle(String title) {
        spec = spec.and((root, query, cb) ->
            cb.equal(cb.lower(root.get("title")), title.toLowerCase()));
    }
}
