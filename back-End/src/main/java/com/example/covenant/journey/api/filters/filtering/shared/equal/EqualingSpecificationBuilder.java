package com.example.covenant.journey.api.filters.filtering.shared.equal;

import com.example.covenant.journey.api.filters.models.FilteringType;
import com.example.covenant.journey.api.filters.models.SearchCriteria;
import com.example.covenant.journey.api.filters.models.SpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;

public class EqualingSpecificationBuilder<Entity> implements SpecificationBuilder<Entity> {

    public static final List<FilteringType> SUPPORTED_OPERATORS = Arrays.asList(
            FilteringType.EQUAL, FilteringType.NOT_EQUAL
    );

    @Override
    public Specification<Entity> buildSpecification(SearchCriteria searchCriteria) {
        return new EqualingSpecification<>(searchCriteria);
    }
}