package com.example.covenant.journey.api.dto.apartment;

import com.example.covenant.journey.api.dto.general.AbstractResponse;
import com.example.covenant.journey.models.apartment.ApartmentType;
import com.example.covenant.journey.models.geodata.GeoData;
import io.swagger.annotations.ApiModelProperty;

public class ApartmentResponse extends AbstractResponse {

	@ApiModelProperty(notes = "Name of the Apartment", example = "Classic hall")
	private String name;

	@ApiModelProperty(notes = "Type of the Apartment", example = "HOTEL")
	private ApartmentType apartmentType;

	@ApiModelProperty(notes = "description", example = "simple description")
	private String description;

	@ApiModelProperty(notes = "Geographical information of the Apartment")
	private GeoData geoData;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ApartmentType getApartmentType() {
		return apartmentType;
	}

	public void setApartmentType(ApartmentType apartmentType) {
		this.apartmentType = apartmentType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public GeoData getGeoData() {
		return geoData;
	}

	public void setGeoData(GeoData geoData) {
		this.geoData = geoData;
	}
}
