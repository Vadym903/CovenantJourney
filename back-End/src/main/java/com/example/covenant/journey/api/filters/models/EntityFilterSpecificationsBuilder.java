package com.example.covenant.journey.api.filters.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface EntityFilterSpecificationsBuilder<T> {

    Logger log = LoggerFactory.getLogger(EntityFilterSpecificationsBuilder.class);

    List<FilterProperty<T>> getFilterableProperties();

    default Specification<T> buildSpecification(List<SearchCriteria> searchCriterias) {
        Specification<T> specification = null;
        for (SearchCriteria searchCriteria : searchCriterias) {
            Optional<FilterProperty<T>> filterEntityProperty = getFilterableProperties().stream().filter(
                    property -> property.getPropertyName().equals(searchCriteria.getKey())
            ).findFirst();

            if (filterEntityProperty.isPresent()) {
                FilterProperty<T> filterProperty1 = filterEntityProperty.get();

                if (specification == null) {
                    specification = Specification.where(filterProperty1.getSpecificationBuilder().buildSpecification(searchCriteria));
                } else {
                    specification = specification.and(filterProperty1.getSpecificationBuilder().buildSpecification(searchCriteria));
                }
            } else {
                log.warn("Filtering on property '{}' has been skipped because it's absent in filterableProperties", searchCriteria.getKey());
            }
        }
        return specification;
    }
}