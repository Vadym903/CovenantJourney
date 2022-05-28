package com.example.covenant.journey.api.filters.filtering.apartment.owner;

import com.example.covenant.journey.api.filters.models.FilteringType;
import com.example.covenant.journey.api.filters.models.SearchCriteria;
import com.example.covenant.journey.api.filters.models.SpecificationBuilder;
import com.example.covenant.journey.model.apartment.Apartment;
import com.example.covenant.journey.model.apartment.feedback.Feedback;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;

public class ApartmentOwnerSpecificationBuilder implements SpecificationBuilder<Apartment> {

	public static final List<FilteringType> SUPPORTED_OPERATORS = Arrays.asList(
			FilteringType.EQUAL);

	@Override
	public Specification<Apartment> buildSpecification(SearchCriteria searchCriteria) {
		return new ApartmentOwnerSpecification(searchCriteria);
	}

}