package com.example.covenant.journey.models.apartment;

import com.example.covenant.journey.models.UserSpecified;
import com.example.covenant.journey.models.geodata.GeoData;
import com.example.covenant.journey.models.user.UserEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "apartment")
public class Apartment implements UserSpecified {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "apartment_name")
	private String name;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "apartment_type")
	private ApartmentType apartmentType;

	@Column(name = "description")
	private String description;

	// TODO add images

	@OneToOne
	private GeoData geoData;

	@OneToOne
	private UserEntity userEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public GeoData getGeoData() {
		return geoData;
	}

	public void setGeoData(GeoData geoData) {
		this.geoData = geoData;
	}

	@Override
	public UserEntity getUser() {
		return userEntity;
	}

	public void setUser(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Apartment apartment = (Apartment) o;
		return Objects.equals(id, apartment.id)
				&& Objects.equals(name, apartment.name)
				&& apartmentType == apartment.apartmentType
				&& Objects.equals(description, apartment.description)
				&& Objects.equals(geoData, apartment.geoData)
				&& Objects.equals(userEntity, apartment.userEntity);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, apartmentType, description, geoData, userEntity);
	}
}
