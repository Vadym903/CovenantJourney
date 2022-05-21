package com.example.covenant.journey.api.filters.filtering.apartment;

import com.example.covenant.journey.api.filters.models.FilterProperty;
import com.example.covenant.journey.api.filters.models.FilteringSpecificationsBuilder;
import com.example.covenant.journey.model.apartment.Apartment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ApartmentSpecificationBuilder implements FilteringSpecificationsBuilder<Apartment> {
	public static final List<FilterProperty<Apartment>> FILTERABLE_PROPERTIES = Arrays.asList(
			new FilterProperty<>("accommodations", String.class,
					ApartmentAccommodationsSpecificationBuilder.SUPPORTED_OPERATORS, new ApartmentAccommodationsSpecificationBuilder())
	);

	@Override
	public List<FilterProperty<Apartment>> getFilterableProperties() {
		return FILTERABLE_PROPERTIES;
	}
}
