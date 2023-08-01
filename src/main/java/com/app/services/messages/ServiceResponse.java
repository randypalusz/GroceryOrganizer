package com.app.services.messages;

import com.app.enums.Status;

// @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class ServiceResponse<T> {
    public T val;
    public String errorMessage;
    public Status status;

    public ServiceResponse(T val, Status status) {
        this.val = val;
        this.errorMessage = status.getMessage();
        this.status = status;
    }

}
