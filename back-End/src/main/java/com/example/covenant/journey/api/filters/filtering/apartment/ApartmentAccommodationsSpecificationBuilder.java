package com.example.covenant.journey.api.filters.filtering.apartment;

import com.example.covenant.journey.api.filters.models.FilteringType;
import com.example.covenant.journey.api.filters.models.SearchCriteria;
import com.example.covenant.journey.api.filters.models.SpecificationBuilder;
import com.example.covenant.journey.model.apartment.Apartment;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;

public class ApartmentAccommodationsSpecificationBuilder implements SpecificationBuilder<Apartment> {

	public static final List<FilteringType> SUPPORTED_OPERATORS = Arrays.asList(
			FilteringType.CONTAIN);

	@Override
	public Specification<Apartment> buildSpecification(SearchCriteria searchCriteria) {
		return new ApartmentAccommodationsSpecification(searchCriteria);
	}

}