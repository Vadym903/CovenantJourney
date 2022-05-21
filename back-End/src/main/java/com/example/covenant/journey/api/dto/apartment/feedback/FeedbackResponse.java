package com.example.covenant.journey.api.dto.apartment.feedback;

import com.example.covenant.journey.api.dto.apartment.ApartmentResponse;
import com.example.covenant.journey.api.dto.general.AbstractResponse;
import com.example.covenant.journey.api.dto.user.UserResponse;
import com.example.covenant.journey.model.apartment.feedback.Feedback;
import io.swagger.annotations.ApiModelProperty;

public class FeedbackResponse extends AbstractResponse {

	@ApiModelProperty(notes = "cleanlinessMark", example = "4")
	private short cleanlinessMark;

	@ApiModelProperty(notes = "locationMark", example = "5")
	private short locationMark;

	@ApiModelProperty(notes = "communicationMark", example = "3")
	private short communicationMark;

	@ApiModelProperty(notes = "serviceMark", example = "4")
	private short serviceMark;

	@ApiModelProperty(notes = "Feedback message", example = "Some message")
	private String description;

	@ApiModelProperty(notes = "Apartment")
	private ApartmentResponse apartment;

	@ApiModelProperty(notes = "User that left this feedback")
	private UserResponse user;

	public FeedbackResponse(Feedback feedback) {
		super(feedback);
		this.cleanlinessMark = feedback.getCleanlinessMark();
		this.locationMark = feedback.getLocationMark();
		this.communicationMark = feedback.getCommunicationMark();
		this.serviceMark = feedback.getServiceMark();
		this.description = feedback.getDescription();
		this.user = new UserResponse(feedback.getUser());
	}

	public short getCleanlinessMark() {
		return cleanlinessMark;
	}

	public void setCleanlinessMark(short cleanlinessMark) {
		this.cleanlinessMark = cleanlinessMark;
	}

	public short getLocationMark() {
		return locationMark;
	}

	public void setLocationMark(short locationMark) {
		this.locationMark = locationMark;
	}

	public short getCommunicationMark() {
		return communicationMark;
	}

	public void setCommunicationMark(short communicationMark) {
		this.communicationMark = communicationMark;
	}

	public short getServiceMark() {
		return serviceMark;
	}

	public void setServiceMark(short serviceMark) {
		this.serviceMark = serviceMark;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ApartmentResponse getApartment() {
		return apartment;
	}

	public void setApartment(ApartmentResponse apartment) {
		this.apartment = apartment;
	}

	public UserResponse getUser() {
		return user;
	}

	public void setUser(UserResponse user) {
		this.user = user;
	}
}
