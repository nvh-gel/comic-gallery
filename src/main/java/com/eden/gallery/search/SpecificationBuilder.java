package com.eden.gallery.search;

import org.springframework.data.jpa.domain.Specification;

public abstract class SpecificationBuilder<T> {

    protected Specification<T> object;

    protected SpecificationBuilder() {
        this.object = defaultSpec();
    }

    public abstract Specification<T> build();

    public Specification<T> defaultSpec() {

        return (t, cq, cb) -> t.isNotNull();
    }
}
