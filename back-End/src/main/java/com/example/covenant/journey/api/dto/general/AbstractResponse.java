package com.example.covenant.journey.api.dto.general;

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

    public AbstractResponse(Long id) {
        this.id = id;
    }
}
