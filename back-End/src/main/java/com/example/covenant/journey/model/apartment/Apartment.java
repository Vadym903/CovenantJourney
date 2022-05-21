package com.example.covenant.journey.model.apartment;

import com.example.covenant.journey.model.AbstractEntity;
import com.example.covenant.journey.model.UserSpecific;
import com.example.covenant.journey.model.apartment.feedback.Feedback;
import com.example.covenant.journey.model.geodata.GeoData;
import com.example.covenant.journey.model.photo.Image;
import com.example.covenant.journey.model.user.User;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "apartment")
public class Apartment implements UserSpecific, AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "apartment_name")
	private String name;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "apartment_type")
	private ApartmentType apartmentType;

	@Column(name = "description")
	@Size(max = 10000)
	private String description;

	@OneToMany
	@JoinTable(name = "apartment_image",
			joinColumns = @JoinColumn(name = "apartment_id", foreignKey = @ForeignKey(name = "fk_apartment_image_to_apartment")),
			inverseJoinColumns = @JoinColumn(name = "image_id", foreignKey = @ForeignKey(name = "fk_apartment_image_to_image")))
	private List<Image> images;

	@OneToMany(mappedBy = "apartment")
	private List<Feedback> feedbacks = new ArrayList<>();

	@Column(name = "accommodations")
	@ElementCollection(targetClass = ApartmentAccommodations.class)
	@CollectionTable(name = "apartment_accommodations", joinColumns = @JoinColumn(name = "apartment_id"))
	@Enumerated(EnumType.STRING)
	private List<ApartmentAccommodations> accommodations;

	@OneToOne(cascade = CascadeType.ALL)
	private GeoData geoData;

	@OneToOne
	private User user;

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

	public List<ApartmentAccommodations> getAccommodations() {
		return accommodations;
	}

	public void setAccommodations(List<ApartmentAccommodations> accommodations) {
		this.accommodations = accommodations;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
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

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	@Override
	public User getUser() {
		return user;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
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
				&& Objects.equals(user, apartment.user);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, apartmentType, description, geoData, user);
	}
}
