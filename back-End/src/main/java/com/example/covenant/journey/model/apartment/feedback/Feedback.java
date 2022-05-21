package com.example.covenant.journey.model.apartment.feedback;

import com.example.covenant.journey.model.AbstractEntity;
import com.example.covenant.journey.model.UserSpecific;
import com.example.covenant.journey.model.apartment.Apartment;
import com.example.covenant.journey.model.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "feedback")
public class Feedback implements UserSpecific, AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "cleanliness_mark")
	private short cleanlinessMark;

	@Column(name = "location_mark")
	private short locationMark;

	@Column(name = "communication_mark")
	private short communicationMark;

	@Column(name = "service_mark")
	private short serviceMark;

	@Column(name = "description")
	@Size(max = 10000)
	private String description;

	@ManyToOne
	private Apartment apartment;

	@OneToOne
	private User user;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public User getUser() {
		return user;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	public short getCleanlinessMark() {
		return cleanlinessMark;
	}

	public void setCleanlinessMark(short cleanlinessMark) {
		this.cleanlinessMark = cleanlinessMark;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Apartment getApartment() {
		return apartment;
	}

	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Feedback feedback = (Feedback) o;
		return cleanlinessMark == feedback.cleanlinessMark
				&& locationMark == feedback.locationMark
				&& communicationMark == feedback.communicationMark
				&& serviceMark == feedback.serviceMark
				&& Objects.equals(id, feedback.id)
				&& Objects.equals(description, feedback.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, cleanlinessMark, locationMark, communicationMark, serviceMark, description);
	}
}
