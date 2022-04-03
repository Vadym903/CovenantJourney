package com.example.covenant.journey.api.dto.general;

import com.example.covenant.journey.api.filters.models.FilteringSpecificationsBuilder;
import com.example.covenant.journey.api.filters.models.SearchCriteria;
import com.example.covenant.journey.api.filters.models.SearchCriteriaParser;
import com.example.covenant.journey.api.shared.ControllerUtil;
import com.example.covenant.journey.model.AbstractEntity;
import com.example.covenant.journey.services.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractController<
		Response extends AbstractResponse,
		Entity extends AbstractEntity,
		Request extends AbstractRequest<Entity>,
		Service extends AbstractService<Entity>> {

	public static final Integer DEFAULT_PAGE_NUMBER = 0;
	public static final Integer DEFAULT_PAGE_SIZE = 8;

	@Autowired
	protected Service service;

	@Autowired
	private SearchCriteriaParser searchCriteriaParser;


	public abstract Response convertEntityToResponse(Entity entity, List<String> expandFields);

	protected abstract FilteringSpecificationsBuilder<Entity> getSpecificationBuilder();

	public Response convertEntityToResponse(Entity entity) {
		return convertEntityToResponse(entity, Collections.emptyList());
	}

	protected void validate(Entity entity, Request request) {

	}

	protected void updateFields(Entity entity, Request request, boolean isCreate) {

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Response create(@Valid @RequestBody Request request) {
		Entity entity = request.createEntity();
		validate(entity, request);
		updateFields(entity, request, true);

		return convertEntityToResponse(service.create(entity));
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponsePage<Response> findItems(
			@RequestParam(required = false) Optional<String> search,
			@RequestParam(required = false) Optional<Integer> pageNo,
			@RequestParam(required = false) Optional<Integer> size,
			Sort sort,
			@RequestParam(required = false) Optional<String> expand) {
		int pageNumber = pageNo.orElse(DEFAULT_PAGE_NUMBER);
		int pageSize = size.orElse(DEFAULT_PAGE_SIZE);

		Pageable page = PageRequest.of(pageNumber, pageSize);

		Page<Entity> entitiesPage = service.findAll(buildSpecification(search), page);
		return buildResponsePage(entitiesPage);
	}

	@GetMapping(value = "/{id}")
	@ResponseBody
	public Response findById(@PathVariable Long id, @RequestParam Optional<String> expand) {
		Entity entity = ControllerUtil.getOrNotFound(service.getOne(id));
		return convertEntityToResponse(entity, ControllerUtil.parseExpandFields(expand));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Long id) {
		ControllerUtil.getOrNotFound(service.getOne(id));
		service.delete(id);
	}

	@PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Response update(@PathVariable Long id, @RequestBody Request request) {
		Entity entity = ControllerUtil.getOrNotFound(service.getOne(id));
		request.updateEntity(entity);
		validate(entity, request);
		updateFields(entity, request, true);
		return convertEntityToResponse(service.update(entity));
	}

	private Specification<Entity> buildSpecification(Optional<String> search) {
		Specification<Entity> specification = null;
		if (search.isPresent() && getSpecificationBuilder() != null) {
			List<SearchCriteria> searchCriteria = searchCriteriaParser
					.parseCriteria(search.get(), getSpecificationBuilder().getFilterableProperties());

			specification = getSpecificationBuilder().buildSpecification(searchCriteria);
		}
		return specification;
	}

	private ResponsePage<Response> buildResponsePage(Page<Entity> entityPage) {
		List<Response> responses = entityPage
				.get()
				.map(this::convertEntityToResponse)
				.collect(Collectors.toList());
		return new ResponsePage<>(entityPage, responses);
	}
}
