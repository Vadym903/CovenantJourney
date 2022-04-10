package com.example.covenant.journey.api.dto.apartment;

import com.example.covenant.journey.api.dto.general.AbstractResponse;
import com.example.covenant.journey.api.dto.geodata.GeoDataResponse;
import com.example.covenant.journey.api.dto.user.UserResponse;
import com.example.covenant.journey.model.apartment.Apartment;
import com.example.covenant.journey.model.apartment.ApartmentType;
import io.swagger.annotations.ApiModelProperty;

public class ApartmentResponse extends AbstractResponse {

	@ApiModelProperty(notes = "Name of the Apartment", example = "Classic hall")
	private String name;

	@ApiModelProperty(notes = "Type of the Apartment", example = "HOTEL")
	private ApartmentType apartmentType;

	@ApiModelProperty(notes = "description", example = "simple description")
	private String description;

	@ApiModelProperty(notes = "Geographical information of the Apartment")
	private GeoDataResponse geoData;

	private UserResponse user;

	public ApartmentResponse(Apartment apartment) {
		super(apartment);
		this.name = apartment.getName();
		this.apartmentType = apartment.getApartmentType();
		this.description = apartment.getDescription();
		this.geoData = new GeoDataResponse(apartment.getGeoData());
		if (apartment.getUser() != null) {
			this.user = new UserResponse(apartment.getUser());
		}
	}

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

	public GeoDataResponse getGeoData() {
		return geoData;
	}

	public void setGeoData(GeoDataResponse geoData) {
		this.geoData = geoData;
	}

	public UserResponse getUser() {
		return user;
	}

	public void setUser(UserResponse user) {
		this.user = user;
	}
}
