package com.example.covenant.journey.api.filters.filtering.apartment.address;

import com.example.covenant.journey.api.filters.models.FilteringType;
import com.example.covenant.journey.api.filters.models.SearchCriteria;
import com.example.covenant.journey.model.apartment.Apartment;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;

public class ApartmentAddressSpecification implements Specification<Apartment> {

	private SearchCriteria searchCriteria;

	public ApartmentAddressSpecification(SearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	@Override
	public Predicate toPredicate(Root<Apartment> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
		String[] searchingValues = ((String) searchCriteria.getValue()).split("\\W+");

		if (FilteringType.CONTAIN == searchCriteria.getFilteringType() && searchingValues.length > 0) {
			ArrayList<Predicate> predicates = new ArrayList<>();

			for (String value : searchingValues) {
				predicates.add(cb.like(cb.lower(root.get("geoData").get("addressName")), "%" + value.toLowerCase() + "%"));
			}

			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		}
		return null;
	}
}
