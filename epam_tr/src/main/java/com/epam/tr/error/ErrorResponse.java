package com.epam.tr.error;

public class ErrorResponse {

    private String details;

    public ErrorResponse(String details) {
        this.details = details;
    }

    public String getDetails() {
        return details;
    }
}
