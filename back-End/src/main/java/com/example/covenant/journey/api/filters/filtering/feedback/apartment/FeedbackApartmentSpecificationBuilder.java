package com.example.covenant.journey.api.filters.filtering.feedback.apartment;

import com.example.covenant.journey.api.filters.models.FilteringType;
import com.example.covenant.journey.api.filters.models.SearchCriteria;
import com.example.covenant.journey.api.filters.models.SpecificationBuilder;
import com.example.covenant.journey.model.apartment.feedback.Feedback;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;

public class FeedbackApartmentSpecificationBuilder implements SpecificationBuilder<Feedback> {

	public static final List<FilteringType> SUPPORTED_OPERATORS = Arrays.asList(
			FilteringType.EQUAL);

	@Override
	public Specification<Feedback> buildSpecification(SearchCriteria searchCriteria) {
		return new FeedbackApartmentSpecification(searchCriteria);
	}

}