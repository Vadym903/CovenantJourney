package com.example.covenant.journey.api.controller.apartment;

import com.example.covenant.journey.api.dto.apartment.ApartmentRequest;
import com.example.covenant.journey.api.dto.apartment.ApartmentResponse;
import com.example.covenant.journey.api.dto.general.AbstractController;
import com.example.covenant.journey.api.filters.models.FilteringSpecificationsBuilder;
import com.example.covenant.journey.api.shared.APIConstants;
import com.example.covenant.journey.model.apartment.Apartment;
import com.example.covenant.journey.services.apartment.ApartmentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = ApartmentController.API_URL)
public class ApartmentController extends AbstractController<ApartmentResponse, Apartment, ApartmentRequest, ApartmentService> {

	public static final String API_URL = APIConstants.APARTMENT_API;

	@Override
	public ApartmentResponse convertEntityToResponse(Apartment entity, List<String> expandFields) {
		return new ApartmentResponse(entity);
	}

	@Override
	protected FilteringSpecificationsBuilder<Apartment> getSpecificationBuilder() {
		return null;
	}
}
