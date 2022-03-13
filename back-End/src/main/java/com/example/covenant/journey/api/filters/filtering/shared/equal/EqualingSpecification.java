package com.example.covenant.journey.api.filters.filtering.shared.equal;

import com.example.covenant.journey.api.filters.models.FilteringType;
import com.example.covenant.journey.api.filters.models.SearchCriteria;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class EqualingSpecification<Entity> implements Specification<Entity> {

    private static final long serialVersionUID = -5803891540465642051L;
    private SearchCriteria searchCriteria;

    public EqualingSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Entity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if (FilteringType.EQUAL == searchCriteria.getFilteringType()) {
            return cb.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue());
        } else if (FilteringType.NOT_EQUAL == searchCriteria.getFilteringType()) {
            return cb.notEqual(root.get(searchCriteria.getKey()), searchCriteria.getValue());
        } else {
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EqualingSpecification)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        final EqualingSpecification otherObject = (EqualingSpecification) o;

        return new EqualsBuilder()
                .append(this.searchCriteria, otherObject.searchCriteria)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(searchCriteria)
                .hashCode();
    }
}