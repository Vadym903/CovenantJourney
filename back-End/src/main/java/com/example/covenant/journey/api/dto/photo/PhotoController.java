package com.example.covenant.journey.api.dto.photo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = PhotoController.URL)
public class PhotoController {

    public static final String URL = "/photos";

    @Autowired
    private PhotoApiService apiService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Photo create(@RequestParam MultipartFile multipartFile) {
        return apiService.create(multipartFile);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ByteArrayResource> get(@PathVariable Long id) {
        return apiService.findById(id);
    }

    @ResponseStatus( HttpStatus.NO_CONTENT )
    @DeleteMapping(value = "/{id}")
    public void delete( @PathVariable Long id ) {
        apiService.deleteById(id);
    }

}
