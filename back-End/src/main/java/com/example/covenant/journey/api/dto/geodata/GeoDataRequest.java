package com.example.covenant.journey.api.dto.geodata;

import com.example.covenant.journey.api.dto.general.AbstractRequest;
import com.example.covenant.journey.model.geodata.GeoData;
import io.swagger.annotations.ApiModelProperty;

public class GeoDataRequest extends AbstractRequest<GeoData> {

	@ApiModelProperty("latitude")
	private Double latitude;

	@ApiModelProperty("longitude")
	private Double longitude;

	@ApiModelProperty("altitude")
	private Double altitude;

	@ApiModelProperty("Full address name on map")
	private String addressName;

	@Override
	public GeoData createEntity() {
		return updateEntity(new GeoData());
	}

	@Override
	public GeoData updateEntity(GeoData entity) {
		entity.setLatitude(this.latitude);
		entity.setLongitude(this.longitude);
		entity.setAltitude(this.altitude);
		entity.setAddressName(this.addressName);
		return entity;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getAltitude() {
		return altitude;
	}

	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
}
