package com.example.covenant.journey.api.dto.photo;

import com.example.covenant.journey.model.photo.ShorterPhotoEntity;

public class Photo {

    private Long id;

    private String type;

    public static ShorterPhotoEntity getShorterPhotoEntity(Photo photo) {
        ShorterPhotoEntity photoEntity = new ShorterPhotoEntity();
        photoEntity.setId(photo.getId());
        photoEntity.setType(photo.getType());
        return photoEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
