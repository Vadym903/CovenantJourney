package com.example.covenant.journey.api.models.general;

import com.example.covenant.journey.models.PrimaryEntity;

public abstract class AbstractConverter<Entity extends PrimaryEntity,
        Response extends PrimaryResponse,
        Request extends PrimaryRequest> {

    public void convertEntityToResponseForAdmin(Entity entity, Response response) {
        convertEntityToResponseForOwner(entity, response);
    }

    public void convertEntityToResponseForOwner(Entity entity, Response response) {
        convertEntityToResponseForAll(entity, response);
    }

    public abstract void convertEntityToResponseForAll(Entity entity, Response response);
}
