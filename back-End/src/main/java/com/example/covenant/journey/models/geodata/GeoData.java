package com.example.covenant.journey.models.geodata;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "geo_data")
public class GeoData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "latitude")
	private Double latitude;

	@Column(name = "longitude")
	private Double longitude;

	@Column(name = "altitude")
	private Double altitude;

	@Column(name = "address_name")
	private String addressName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GeoData geoData = (GeoData) o;
		return Objects.equals(id, geoData.id)
				&& Objects.equals(latitude, geoData.latitude)
				&& Objects.equals(longitude, geoData.longitude)
				&& Objects.equals(addressName, geoData.addressName)
				&& Objects.equals(altitude, geoData.altitude);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, latitude, longitude, altitude, addressName);
	}

	@Override
	public String toString() {
		return "GeoData{" +
				"id=" + id +
				", latitude=" + latitude +
				", longitude=" + longitude +
				", altitude=" + altitude +
				", addressName=" + addressName +
				'}';
	}
}
