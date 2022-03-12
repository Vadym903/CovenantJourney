package com.example.covenant.journey.api.models.general;

import com.example.covenant.journey.models.PrimaryEntity;
import com.example.covenant.journey.services.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

public class AbstractCRUDController<
        Response extends PrimaryResponse,
        Entity extends PrimaryEntity,
        Request extends PrimaryRequest,
        Service extends AbstractService<Entity>,
        ApiService extends AbstractApiService<Entity, Response, Request, Service>> {

    @Autowired
    protected ApiService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Response create(@Valid @RequestBody Request request) {
        return service.create(request);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public AbstractResponsePage<Response> findItems(
            @RequestParam(required = false) Optional<String> search,

            @RequestParam(required = false) Optional<Integer> pageNo,

            @RequestParam(required = false) Optional<Integer> pageSize,
            Sort sort,
            @RequestParam(required = false) Optional<String> expand) {
        return service.getAllItems(search, pageNo, pageSize, sort, expand);
    }

    @GetMapping(value = "/{id}")
    public Response findById(@PathVariable Long id, @RequestParam Optional<String> expand) {
        return service.findById(id, expand);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response update(@PathVariable Long id, @RequestBody Request request) {
        return service.update(id, request);
    }
}
