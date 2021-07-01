package com.epam.tr.dto;

public class ErrorResponse {

    private String details;

    public ErrorResponse(String details) {
        this.details = details;
    }

    public String getDetails() {
        return details;
    }
}
