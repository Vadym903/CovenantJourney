package com.example.covenant.journey.api.filters.models;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {

    Specification<T> buildSpecification(SearchCriteria searchCriteria);

}