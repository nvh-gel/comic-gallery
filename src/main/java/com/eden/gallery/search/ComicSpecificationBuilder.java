package com.eden.gallery.search;

import com.eden.gallery.model.Comic;
import com.eden.gallery.viewmodel.ComicVM;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

public class ComicSpecificationBuilder extends SpecificationBuilder<Comic> {

    @SuppressWarnings("unused")
    public ComicSpecificationBuilder() {
        super();
    }

    public ComicSpecificationBuilder(ComicVM searchCriteria) {
        super();
        this.from(searchCriteria);
    }

    /**
     * Produce query specification for comic.
     *
     * @return query specification
     */
    @Override
    public Specification<Comic> build() {
        return Specification.where(object);
    }

    public void from(@NonNull ComicVM searchCriteria) {
        if (searchCriteria.getName() != null) {
            hasName(searchCriteria.getName());
        }
        if (searchCriteria.getAuthor() != null) {
            hasAuthor(searchCriteria.getAuthor());
        }
        if (searchCriteria.getDescription() != null) {
            descriptionContains(searchCriteria.getDescription());
        }
    }

    public void hasName(String name) {

        this.object = this.object.and((comic, cq, cb) ->
                cb.like(comic.get("name"), "%" + name.trim() + "%"));
    }

    public void hasAuthor(String author) {
        this.object = this.object.and((comic, cq, cb) ->
                cb.like(comic.get("author"), "%" + author.trim() + "%"));
    }

    public void descriptionContains(String desc) {

        this.object = this.object.and((comic, cq, cb) ->
                cb.like(comic.get("description"), "%" + desc.trim() + "%"));
    }
}
