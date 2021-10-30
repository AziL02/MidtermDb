package com.example.MidtermDb.Exeption;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
    public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException() {
        super();
    }
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
