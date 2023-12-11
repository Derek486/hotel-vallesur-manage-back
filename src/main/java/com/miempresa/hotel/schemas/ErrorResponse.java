package com.miempresa.hotel.schemas;

public class ErrorResponse {
    private String message;

    public ErrorResponse() {
        // Constructor por defecto necesario para la deserialización
    }

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
