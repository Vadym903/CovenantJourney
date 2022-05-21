package com.example.covenant.journey.api.filters.filtering.feedback;

import com.example.covenant.journey.api.filters.models.FilterProperty;
import com.example.covenant.journey.api.filters.models.FilteringSpecificationsBuilder;
import com.example.covenant.journey.model.apartment.feedback.Feedback;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class FeedbackSpecificationBuilder implements FilteringSpecificationsBuilder<Feedback> {

	public static final List<FilterProperty<Feedback>> FILTERABLE_PROPERTIES = Arrays.asList(
			new FilterProperty<>("apartment", Long.class,
					FeedbackApartmentSpecificationBuilder.SUPPORTED_OPERATORS, new FeedbackApartmentSpecificationBuilder())
	);

	@Override
	public List<FilterProperty<Feedback>> getFilterableProperties() {
		return FILTERABLE_PROPERTIES;
	}
}
