package com.example.covenant.journey.api.dto.general;

import com.example.covenant.journey.models.AbstractEntity;

public abstract class AbstractConverter<Entity extends AbstractEntity,
        Response extends AbstractResponse,
        Request extends AbstractRequest> {

    public void convertEntityToResponseForAdmin(Entity entity, Response response) {
        convertEntityToResponseForOwner(entity, response);
    }

    public void convertEntityToResponseForOwner(Entity entity, Response response) {
        convertEntityToResponseForAll(entity, response);
    }

    public abstract void convertEntityToResponseForAll(Entity entity, Response response);
}
