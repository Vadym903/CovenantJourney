package com.example.covenant.journey.api.dto.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class HttpStatusException extends HttpStatusCodeException {

    public HttpStatusException(HttpStatus statusCode) {
        super(statusCode);
    }
}
