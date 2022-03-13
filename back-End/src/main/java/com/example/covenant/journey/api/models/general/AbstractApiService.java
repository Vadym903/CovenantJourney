package com.example.covenant.journey.api.models.general;

import com.example.covenant.journey.api.filters.models.EntityFilterSpecificationsBuilder;
import com.example.covenant.journey.api.filters.models.FilterProperty;
import com.example.covenant.journey.api.filters.models.SearchCriteria;
import com.example.covenant.journey.api.filters.models.SearchCriteriaKeyFilter;
import com.example.covenant.journey.models.PrimaryEntity;
import com.example.covenant.journey.services.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractApiService<Entity extends PrimaryEntity,
        Response extends PrimaryResponse,
        Request extends PrimaryRequest,
        Service extends AbstractService<Entity>> {

    public static final Integer DEFAULT_PAGE_NUMBER = 0;
    public static final Integer DEFAULT_PAGE_SIZE = 8;
    public static final String NON_FOUND_MESSAGE = "Item was not found";

    @Autowired
    protected Service service;

    @Autowired
    private SearchCriteriaKeyFilter searchCriteriaKeyFilter;

    public Response create(Request request) {
        return convertEntityToResponse(service.create(convertRequestToEntity(request)), Optional.empty());
    }

    @Transactional
    public Response update(Long id, Request request) {
        Entity entity = getValidEntityById(id);
        copyProperties(request, entity);
        service.update(entity);
        return convertEntityToResponse(getValidEntityById(id), Optional.empty());
    }

    public void delete(Long id) {
        service.delete(id);
    }

    public Response findById(Long id, Optional<String> expand) {
        return convertEntityToResponse(getValidEntityById(id), expand);
    }

    public AbstractResponsePage<Response> getAllItems(Optional<String> search, Optional<Integer> pageNo,
                                                      Optional<Integer> pageSize, Sort sort, Optional<String> expand) {

        Specification<Entity> specification = null;
        if (search.isPresent()) {
            List<FilterProperty<Entity>> filterEntityProperties = getSpecificationBuilder().getFilterableProperties();
            List<SearchCriteria> searchCriteria = searchCriteriaKeyFilter.filtrateCriteria(search.get(), filterEntityProperties);
            specification = getSpecificationBuilder().buildSpecification(searchCriteria);
        }

        return getResponsePage(specification, pageNo.orElse(DEFAULT_PAGE_NUMBER),
                pageSize.orElse(DEFAULT_PAGE_SIZE), sort, expand);
    }

    public AbstractResponsePage<Response> getResponsePage(Specification<Entity> filteringSpecification, Integer pageNo,
                                                      Integer pageSize, Sort sort, Optional<String> expand) {
        sort = getSortedByDefault(sort);
        Pageable paging = PageRequest.of(pageNo, pageSize, sort);
        Page<Entity> pagedResult = service.findAll(filteringSpecification, paging);
        List<Response> responses = pagedResult.getContent().stream().map(item -> convertEntityToResponse(item, expand))
                .filter(Objects::nonNull).collect(Collectors.toList());
        return new AbstractResponsePage<>(responses, pagedResult.getTotalElements());
    }

    protected Sort getSortedByDefault(Sort sort) {
        return sort.and(Sort.by(Sort.Direction.ASC, "id"));
    }

    public List<Response> getAll() {
        return service.findAll().stream().map(item -> convertEntityToResponse(item, Optional.empty())).collect(Collectors.toList());
    }

    protected Entity getValidEntityById(Long id) {
        Entity entity = service.findById(id);
        if (entity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "object wasn't found");
        }

        return entity;
    }

     protected abstract EntityFilterSpecificationsBuilder<Entity> getSpecificationBuilder();

    protected abstract Entity convertRequestToEntity(Request request);

    protected abstract void copyProperties(Request request, Entity entity);

    protected abstract Response convertEntityToResponse(Entity entity, Optional<String> expand);
}
