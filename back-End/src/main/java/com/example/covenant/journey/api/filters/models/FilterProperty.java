package com.example.covenant.journey.api.filters.models;

import java.util.List;

public class FilterProperty<T> {
    private String propertyName;
    private Class<?> type;
    private List<FilteringType> filteringTypes;
    private SpecificationBuilder<T> specificationBuilder;

    public FilterProperty(String propertyName, Class<?> type, List<FilteringType> filteringTypes, SpecificationBuilder<T> specificationBuilder) {
        this.propertyName = propertyName;
        this.type = type;
        this.filteringTypes = filteringTypes;
        this.specificationBuilder = specificationBuilder;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public Class<?> getType() {
        return type;
    }

    public List<FilteringType> getFilteringTypes() {
        return filteringTypes;
    }

    public SpecificationBuilder<T> getSpecificationBuilder() {
        return specificationBuilder;
    }
}
