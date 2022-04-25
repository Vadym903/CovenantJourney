package com.example.covenant.journey.api.dto.photo;

import com.example.covenant.journey.model.photo.Image;
import com.example.covenant.journey.services.photo.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping(value = ImageController.URL)
public class ImageController {

	public static final String URL = "/photos";

	@Autowired
	private ImageService service;

	@GetMapping("/{name}")
	public ResponseEntity<ByteArrayResource> get(@PathVariable String name) throws FileNotFoundException {
		Image image = service.getOne((root, cq, cb) -> cb.equal(root.get("imageName"), name));
		return new ResponseEntity<>(service.getImageByName(name), buildImageHeaders(image), HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

	private HttpHeaders buildImageHeaders(Image image) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment");
		httpHeaders.add(HttpHeaders.CONTENT_TYPE, image.getFormat());
		httpHeaders.add(HttpHeaders.CACHE_CONTROL, CacheControl.noCache().getHeaderValue());
		httpHeaders.setContentDispositionFormData("attachment", image.getImageName());
		return httpHeaders;
	}
}
