package com.example.covenant.journey.api.dto.apartment;

import com.example.covenant.journey.api.dto.general.AbstractRequest;
import com.example.covenant.journey.api.dto.geodata.GeoDataRequest;
import com.example.covenant.journey.model.apartment.Apartment;
import com.example.covenant.journey.model.apartment.ApartmentAccommodations;
import com.example.covenant.journey.model.apartment.ApartmentType;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ApartmentRequest extends AbstractRequest<Apartment> {

	@ApiModelProperty(notes = "Name of the Apartment", example = "Classic hall")
	private String name;

	@ApiModelProperty(notes = "Type of the Apartment", example = "HOTEL")
	private ApartmentType apartmentType;

	@ApiModelProperty(notes = "description", example = "simple description")
	private String description;

	@ApiModelProperty(notes = "Geographical information of the Apartment")
	@NotNull
	private GeoDataRequest geoData;

	@ApiModelProperty(notes = "Apartment accommodations")
	private List<ApartmentAccommodations> accommodations;

	@Override
	public Apartment createEntity() {
		return updateEntity(new Apartment());
	}

	@Override
	public Apartment updateEntity(Apartment entity) {
		entity.setName(this.name);
		entity.setApartmentType(this.apartmentType);
		entity.setDescription(this.description);
		entity.setGeoData(this.geoData.createEntity());
		entity.setAccommodations(this.accommodations);
		return entity;
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

	public GeoDataRequest getGeoData() {
		return geoData;
	}

	public void setGeoData(GeoDataRequest geoData) {
		this.geoData = geoData;
	}

	public List<ApartmentAccommodations> getAccommodations() {
		return accommodations;
	}

	public void setAccommodations(List<ApartmentAccommodations> accommodations) {
		this.accommodations = accommodations;
	}
}
