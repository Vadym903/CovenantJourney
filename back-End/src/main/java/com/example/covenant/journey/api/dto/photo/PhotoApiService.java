//package com.example.covenant.journey.api.dto.photo;
//
//import com.example.covenant.journey.model.photo.Image;
//import com.example.covenant.journey.services.photo.ImageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//
//@Service
//public class PhotoApiService {
//
//    private final ArrayList<String> ALLOWED_FORMATS = new ArrayList<>(Arrays.asList("image/jpeg", "image/png"));
//
//    @Autowired
//    private ImageService imageService;
//
//    public Photo create(MultipartFile multipartFile) {
//        validateFile(multipartFile);
//        Image imageEntity = new Image();
//        imageEntity.setFormat(multipartFile.getContentType());
//        try {
//            imageEntity.setBytes(multipartFile.getBytes());
//        } catch (IOException e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "failed to read file");
//        }
//        return getPhoto(imageService.create(imageEntity));
//    }
//
//    public void deleteById(Long id) {
//        imageService.delete(id);
//    }
//
//    public ResponseEntity<ByteArrayResource> findById(Long id) {
//        Image imageEntity = findValidPhotoEntityById(id);
//        HttpHeaders headers = new HttpHeaders();
//
//        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment");
//        headers.add(HttpHeaders.CONTENT_TYPE, imageEntity.getFormat());
//        //headers.add(HttpHeaders.CACHE_CONTROL,);  TODO
//
//        return new ResponseEntity<ByteArrayResource>(new ByteArrayResource(imageEntity.getBytes()), headers, HttpStatus.OK);
//    }
//
//    private void validateFile(MultipartFile multipartFile) {
//        if (!ALLOWED_FORMATS.contains(multipartFile.getContentType())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "wrong file type");
//        }
//    }
//
//    private Photo getPhoto(Image imageEntity) {
//        Photo photo = new Photo();
//        photo.setId(imageEntity.getId());
//        photo.setType(imageEntity.getFormat());
//        return photo;
//    }
//
//    private Image findValidPhotoEntityById(Long id) {
//        Image imageEntity = imageService.getOne(id);
//        if (imageEntity != null) {
//            return imageEntity;
//        }
//        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "photo wasn't found");
//    }
//
//}
