package com.example.covenant.journey.api.filters.filtering.feedback.owner;

import com.example.covenant.journey.api.filters.models.FilteringType;
import com.example.covenant.journey.api.filters.models.SearchCriteria;
import com.example.covenant.journey.model.apartment.feedback.Feedback;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class FeedbackOwnerSpecification implements Specification<Feedback> {

	private SearchCriteria searchCriteria;

	public FeedbackOwnerSpecification(SearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	@Override
	public Predicate toPredicate(Root<Feedback> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
		Long id = Long.parseLong(searchCriteria.getValue().toString());

		if (FilteringType.EQUAL == searchCriteria.getFilteringType()) {
			return criteriaBuilder.equal(root.get("apartment").get("user").get("id"), id);
		}
		return null;
	}
}
