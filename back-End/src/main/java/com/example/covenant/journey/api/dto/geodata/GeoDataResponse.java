package com.example.covenant.journey.api.dto.geodata;

import com.example.covenant.journey.api.dto.general.AbstractResponse;
import com.example.covenant.journey.model.geodata.GeoData;
import io.swagger.annotations.ApiModelProperty;

public class GeoDataResponse extends AbstractResponse {

	@ApiModelProperty("latitude")
	private Double latitude;

	@ApiModelProperty("longitude")
	private Double longitude;

	@ApiModelProperty("altitude")
	private Double altitude;

	@ApiModelProperty("addressName")
	private String addressName;

	public GeoDataResponse() {
	}

	public GeoDataResponse(GeoData geoData) {
		super(geoData);
		this.latitude = geoData.getLatitude();
		this.longitude = geoData.getLongitude();
		this.altitude = geoData.getAltitude();
		this.addressName = geoData.getAddressName();
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
