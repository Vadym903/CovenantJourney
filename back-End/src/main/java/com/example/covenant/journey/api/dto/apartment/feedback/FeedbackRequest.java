package com.example.covenant.journey.api.dto.apartment.feedback;

import com.example.covenant.journey.api.dto.general.AbstractRequest;
import com.example.covenant.journey.model.apartment.feedback.Feedback;
import io.swagger.annotations.ApiModelProperty;

public class FeedbackRequest extends AbstractRequest<Feedback> {

	@ApiModelProperty(notes = "description", example = "4")
	private short cleanlinessMark;

	@ApiModelProperty(notes = "description", example = "5")
	private short locationMark;

	@ApiModelProperty(notes = "description", example = "3")
	private short communicationMark;

	@ApiModelProperty(notes = "description", example = "4")
	private short serviceMark;

	@ApiModelProperty(notes = "description", example = "simple description")
	private String description;

	@ApiModelProperty(notes = "Id of the apartment", example = "1")
	private Long apartmentId;

	@Override
	public Feedback createEntity() {
		return updateEntity(new Feedback());
	}

	@Override
	public Feedback updateEntity(Feedback entity) {
		entity.setDescription(this.description);
		entity.setCleanlinessMark(this.cleanlinessMark);
		entity.setLocationMark(this.locationMark);
		entity.setCommunicationMark(this.communicationMark);
		entity.setServiceMark(this.serviceMark);
		return entity;
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

	public Long getApartmentId() {
		return apartmentId;
	}

	public void setApartmentId(Long apartmentId) {
		this.apartmentId = apartmentId;
	}
}
