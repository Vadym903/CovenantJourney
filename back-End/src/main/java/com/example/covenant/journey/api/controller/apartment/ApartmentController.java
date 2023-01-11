package com.example.covenant.journey.api.controller.apartment;

import com.example.covenant.journey.api.dto.apartment.ApartmentRequest;
import com.example.covenant.journey.api.dto.apartment.ApartmentResponse;
import com.example.covenant.journey.api.dto.general.AbstractController;
import com.example.covenant.journey.api.filters.filtering.apartment.ApartmentSpecificationBuilder;
import com.example.covenant.journey.api.filters.models.FilteringSpecificationsBuilder;
import com.example.covenant.journey.api.shared.APIConstants;
import com.example.covenant.journey.model.apartment.Apartment;
import com.example.covenant.journey.model.photo.Image;
import com.example.covenant.journey.model.photo.ImageType;
import com.example.covenant.journey.services.apartment.ApartmentService;
import com.example.covenant.journey.services.photo.ImageService;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = ApartmentController.API_URL)
public class ApartmentController extends AbstractController<ApartmentResponse, Apartment, ApartmentRequest, ApartmentService> {

	public static final String API_URL = APIConstants.APARTMENT_API;

	@Autowired
	private ApartmentSpecificationBuilder apartmentSpecificationBuilder;

	@Autowired
	private ImageService imageService;

	@Override
	public ApartmentResponse convertEntityToResponse(Apartment entity, List<String> expandFields) {
		return new ApartmentResponse(entity);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(path = "images/{apartmentId}",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	@ResponseBody
	public ApartmentResponse uploadImages(@Valid @PathVariable Long apartmentId,
									@RequestParam("files") Optional<MultipartFile[]> files) {
		Apartment entity = service.getOne(apartmentId);
		entity.setImages(saveImages(files));
		
		Boolean extra = true;

		return convertEntityToResponse(service.update(entity));
	}

	@Override
	protected FilteringSpecificationsBuilder<Apartment> getSpecificationBuilder() {
		return apartmentSpecificationBuilder;
	}

	private List<Image> saveImages(Optional<MultipartFile[]> files) {
		return files.map(multipartFiles -> Stream.of(multipartFiles)
				.map(file -> imageService.saveImage(file, ImageType.APARTMENT))
				.collect(Collectors.toList()))
				.orElse(Collections.emptyList());

	}
}
