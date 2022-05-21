package com.example.covenant.journey.api.filters.filtering.feedback;

import com.example.covenant.journey.api.filters.models.FilteringType;
import com.example.covenant.journey.api.filters.models.SearchCriteria;
import com.example.covenant.journey.model.apartment.feedback.Feedback;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class FeedbackApartmentSpecification implements Specification<Feedback> {

	private SearchCriteria searchCriteria;

	public FeedbackApartmentSpecification(SearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	@Override
	public Predicate toPredicate(Root<Feedback> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
		Long feedbackId = ((Long) searchCriteria.getValue());

		if (FilteringType.EQUAL == searchCriteria.getFilteringType()) {
			return criteriaBuilder.equal(root.get("apartment").get("id"), feedbackId);
		}
		return null;
	}
}
