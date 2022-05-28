package com.example.covenant.journey.api.filters.filtering.apartment.owner;

import com.example.covenant.journey.api.filters.models.FilteringType;
import com.example.covenant.journey.api.filters.models.SearchCriteria;
import com.example.covenant.journey.model.apartment.Apartment;
import com.example.covenant.journey.model.apartment.feedback.Feedback;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ApartmentOwnerSpecification implements Specification<Apartment> {

	private SearchCriteria searchCriteria;

	public ApartmentOwnerSpecification(SearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	@Override
	public Predicate toPredicate(Root<Apartment> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
		Long id = Long.parseLong(searchCriteria.getValue().toString());

		if (FilteringType.EQUAL == searchCriteria.getFilteringType()) {
			return criteriaBuilder.equal(root.get("user").get("id"), id);
		}
		return null;
	}
}
