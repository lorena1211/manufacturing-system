package com.system.billing.backend.client;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(Long id) {
        super("No client found with id " + id);
    }
    
}
