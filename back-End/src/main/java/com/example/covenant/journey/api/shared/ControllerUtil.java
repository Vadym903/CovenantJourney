package com.example.covenant.journey.api.shared;

import com.example.covenant.journey.models.AbstractEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ControllerUtil {

	public static <T extends AbstractEntity> T getOrNotFound(T entity) {
		if (entity != null) {
			return entity;
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity wasn't found");
	}

	public static List<String> parseExpandFields(Optional<String> expandFields) {
		return expandFields.map(field -> Arrays.asList(field.split(","))).orElse(Collections.emptyList());
	}
}
