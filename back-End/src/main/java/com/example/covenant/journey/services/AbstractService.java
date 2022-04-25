package com.example.covenant.journey.services;

import com.example.covenant.journey.model.AbstractEntity;
import com.example.covenant.journey.model.UserSpecific;
import com.example.covenant.journey.repositories.BaseRepository;
import com.example.covenant.journey.services.user.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
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

public abstract class AbstractService<Entity extends AbstractEntity> {

	protected static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

	@Autowired
	private CurrentUserService currentUserService;

	protected abstract BaseRepository<Entity> getRepository();

	public void beforeCreate(Entity entity) {
		if (isUserSpecific()) {
			((UserSpecific) entity).setUser(currentUserService.getCurrentUser());
		}
	}

	public void beforeUpdate(Entity entity) {

	}

	@Transactional
	public Entity create(Entity entity) {
		beforeCreate(entity);
		validate(entity);
		return getRepository().save(entity);
	}

	@Transactional
	public Entity update(Entity entity) {
		beforeUpdate(entity);
		validate(entity);
		return getRepository().save(entity);
	}

	public void delete(Long id) {
		getRepository().deleteById(id);
	}

	public Entity getOne(Long id) {
		return getRepository().findById(id).orElse(null);
	}

	public Entity getOne(Specification<Entity> specification) {
		return getRepository().findOne(specification).orElse(null);
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

	private Class<Entity> getEntityTypeClass() {
		@SuppressWarnings("unchecked")
		Class<Entity>[] classes = (Class<Entity>[]) GenericTypeResolver.resolveTypeArguments(getClass(), AbstractService.class);
		return classes[0];
	}

	private boolean isUserSpecific() {
		return UserSpecific.class.isAssignableFrom(getEntityTypeClass());
	}
}
