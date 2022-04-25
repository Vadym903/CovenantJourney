package com.example.covenant.journey.model.photo;

import com.example.covenant.journey.model.AbstractEntity;
import com.example.covenant.journey.model.UserSpecific;
import com.example.covenant.journey.model.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "covenant_image")
public class Image implements AbstractEntity, UserSpecific {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "image_name")
	private String imageName;

	@NotBlank
	@Column(name = "format")
	private String format;

	@Column(name = "original_name")
	private String originalName;

	@Column(name = "image_type")
	private ImageType imageType;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFormat() {
		return format;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	@Override
	public User getUser() {
		return user;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	public ImageType getImageType() {
		return imageType;
	}

	public void setImageType(ImageType imageType) {
		this.imageType = imageType;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public User getUserEntity() {
		return user;
	}

	public void setUserEntity(User user) {
		this.user = user;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Image that = (Image) o;
		return Objects.equals(id, that.id)
				&& Objects.equals(format, that.format)
				&& Objects.equals(imageType, that.imageType)
				&& Objects.equals(imageName, that.imageName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, format, imageName, imageType);
	}
}
