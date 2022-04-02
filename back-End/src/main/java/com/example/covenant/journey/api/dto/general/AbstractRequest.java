package com.example.covenant.journey.api.dto.general;

import com.example.covenant.journey.model.AbstractEntity;

public abstract class AbstractRequest <Entity extends AbstractEntity> {

	public abstract Entity createEntity();

	public abstract Entity updateEntity(Entity entity);
}
