package com.app.errors;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.Objects;

public class ApiError {
    // NOTE: these are all public to allow the exception handlers to access them.
    // if marked private, we can't automatically convert this POJO to json,
    // and the exception handler won't be called
    public String message;
    public Timestamp timestamp;
    public HttpStatus httpStatus;

    public ApiError(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ApiError apiError = (ApiError) o;
        return Objects.equals(message, apiError.message) && httpStatus == apiError.httpStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, httpStatus);
    }
}
