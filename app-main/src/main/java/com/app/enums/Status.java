package com.app.enums;

public enum Status {
    SUCCESS("Success"),
    OTHER_ERROR("Unresolved Error"),
    DUPLICATE_EXISTS("Duplicate Item Exists in DB");

    final String message;

    Status(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
