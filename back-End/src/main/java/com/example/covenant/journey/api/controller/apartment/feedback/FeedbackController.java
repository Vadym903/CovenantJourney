package com.example.covenant.journey.api.controller.apartment.feedback;

import com.example.covenant.journey.api.dto.apartment.ApartmentResponse;
import com.example.covenant.journey.api.dto.apartment.feedback.FeedbackRequest;
import com.example.covenant.journey.api.dto.apartment.feedback.FeedbackResponse;
import com.example.covenant.journey.api.dto.general.AbstractController;
import com.example.covenant.journey.api.filters.filtering.feedback.FeedbackSpecificationBuilder;
import com.example.covenant.journey.api.filters.models.FilteringSpecificationsBuilder;
import com.example.covenant.journey.api.shared.APIConstants;
import com.example.covenant.journey.model.apartment.feedback.Feedback;
import com.example.covenant.journey.services.apartment.ApartmentService;
import com.example.covenant.journey.services.apartment.feedback.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = FeedbackController.API_URL)
public class FeedbackController extends AbstractController<FeedbackResponse, Feedback, FeedbackRequest, FeedbackService> {

	public static final String API_URL = APIConstants.FEEDBACK_API;

	@Autowired
	private ApartmentService apartmentService;

	@Autowired
	private FeedbackSpecificationBuilder feedbackSpecificationBuilder;

	@Override
	public FeedbackResponse convertEntityToResponse(Feedback entity, List<String> expandFields) {
		FeedbackResponse response = new FeedbackResponse(entity);
		if (expandFields.contains("apartment")) {
			response.setApartment(new ApartmentResponse(entity.getApartment()));
		}
		return response;
	}

	@Override
	protected void updateFields(Feedback entity, FeedbackRequest request, boolean isCreate) {
		entity.setApartment(apartmentService.getOne(request.getApartmentId()));
	}

	@Override
	protected FilteringSpecificationsBuilder<Feedback> getSpecificationBuilder() {
		return feedbackSpecificationBuilder;
	}
}
