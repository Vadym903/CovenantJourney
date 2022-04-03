package com.example.covenant.journey.api.dto.general;

import com.example.covenant.journey.model.AbstractEntity;
import io.swagger.annotations.ApiModelProperty;

public abstract class AbstractResponse {

    @ApiModelProperty("Unique identifier")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AbstractResponse() {
    }

    public AbstractResponse(AbstractEntity entity) {
        this.id = entity.getId();
    }
}
