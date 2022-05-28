package com.example.covenant.journey.api.filters.filtering.apartment.accommodations;

import com.example.covenant.journey.api.filters.models.FilteringType;
import com.example.covenant.journey.api.filters.models.SearchCriteria;
import com.example.covenant.journey.model.apartment.Apartment;
import com.example.covenant.journey.model.apartment.ApartmentAccommodations;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;

public class ApartmentAccommodationsSpecification implements Specification<Apartment> {

	private SearchCriteria searchCriteria;

	public ApartmentAccommodationsSpecification(SearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	@Override
	public Predicate toPredicate(Root<Apartment> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
		String[] searchingValues = ((String) searchCriteria.getValue()).split("\\+");

		if (FilteringType.CONTAIN == searchCriteria.getFilteringType() && searchingValues.length > 0) {
			ArrayList<Predicate> predicates = new ArrayList<>();

			for (String value : searchingValues) {
				predicates.add(root.join("accommodations").in(EnumUtils.getEnum(ApartmentAccommodations.class, value)));
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
		}
		return null;
	}
}
