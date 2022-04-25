package com.example.covenant.journey.api.dto.photo;

import com.example.covenant.journey.api.dto.general.AbstractResponse;
import com.example.covenant.journey.model.photo.Image;
import com.example.covenant.journey.model.photo.ImageType;

public class ImageResponse extends AbstractResponse {

	private Long id;

	private String imageName;

	private String format;

	private String originalName;

	private ImageType imageType;

	public ImageResponse(Image image) {
		super(image);
		this.imageName = image.getImageName();
		this.format = image.getFormat();
		this.originalName = image.getOriginalName();
		this.imageType = image.getImageType();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public ImageType getImageType() {
		return imageType;
	}

	public void setImageType(ImageType imageType) {
		this.imageType = imageType;
	}
}
