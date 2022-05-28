package com.example.covenant.journey.api.filters.filtering.apartment.name;

import com.example.covenant.journey.api.filters.models.FilteringType;
import com.example.covenant.journey.api.filters.models.SearchCriteria;
import com.example.covenant.journey.model.apartment.Apartment;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ApartmentNameSpecification implements Specification<Apartment> {

	private SearchCriteria searchCriteria;

	public ApartmentNameSpecification(SearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	@Override
	public Predicate toPredicate(Root<Apartment> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
		String searchingValues = (String) searchCriteria.getValue();

		if (FilteringType.CONTAIN == searchCriteria.getFilteringType()) {
			return cb.like(cb.lower(root.get("name")), "%" + searchingValues.toLowerCase() + "%");
		}
		return null;
	}
}
