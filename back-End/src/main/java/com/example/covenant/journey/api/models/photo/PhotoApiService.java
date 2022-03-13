package com.example.covenant.journey.api.models.photo;

import com.example.covenant.journey.models.photo.PhotoEntity;
import com.example.covenant.journey.services.photo.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class PhotoApiService {

    private final ArrayList<String> ALLOWED_FORMATS = new ArrayList<>(Arrays.asList("image/jpeg", "image/png"));

    @Autowired
    private PhotoService photoService;

    public Photo create(MultipartFile multipartFile) {
        validateFile(multipartFile);
        PhotoEntity photoEntity = new PhotoEntity();
        photoEntity.setType(multipartFile.getContentType());
        try {
            photoEntity.setBytes(multipartFile.getBytes());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "failed to read file");
        }
        return getPhoto(photoService.create(photoEntity));
    }

    public void deleteById(Long id) {
        photoService.delete(id);
    }

    public ResponseEntity<ByteArrayResource> findById(Long id) {
        PhotoEntity photoEntity = findValidPhotoEntityById(id);
        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment");
        headers.add(HttpHeaders.CONTENT_TYPE, photoEntity.getType());
        //headers.add(HttpHeaders.CACHE_CONTROL,);  TODO

        return new ResponseEntity<ByteArrayResource>(new ByteArrayResource(photoEntity.getBytes()), headers, HttpStatus.OK);
    }

    private void validateFile(MultipartFile multipartFile) {
        if (!ALLOWED_FORMATS.contains(multipartFile.getContentType())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "wrong file type");
        }
    }

    private Photo getPhoto(PhotoEntity photoEntity) {
        Photo photo = new Photo();
        photo.setId(photoEntity.getId());
        photo.setType(photoEntity.getType());
        return photo;
    }

    private PhotoEntity findValidPhotoEntityById(Long id) {
        PhotoEntity photoEntity = photoService.findById(id);
        if (photoEntity != null) {
            return photoEntity;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "photo wasn't found");
    }

}
