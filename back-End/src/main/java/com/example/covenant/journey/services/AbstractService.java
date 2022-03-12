package com.example.covenant.journey.services;

import com.example.covenant.journey.models.PrimaryEntity;
import com.example.covenant.journey.repositories.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

public abstract class AbstractService<Entity extends PrimaryEntity> {

    protected static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    protected abstract BaseRepository<Entity> getRepository();

    public Entity create(Entity entity) {
        validate(entity);
        return getRepository().save(entity);
    }

    @Transactional
    public void update(Entity entity) {
        validate(entity);
        getRepository().save(entity);
    }

    public void delete(Long id) {
        getRepository().deleteById(id);
    }

    public Entity findById(Long id) {
        return getRepository().findById(id).orElse(null);
    }

    public Page<Entity> findAll(Specification<Entity> specification, Pageable pageable) {
        return getRepository().findAll(specification, pageable);
    }

    public List<Entity> findAll() {
        return getRepository().findAll();
    }

    protected void validate(Entity entity) {
        Set<ConstraintViolation<Entity>> violations = VALIDATOR.validate(entity);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
