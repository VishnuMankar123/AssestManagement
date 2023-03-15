package com.inventory.assetmanagement.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String resourceType) {
        super(String.format("resource of the type: %s was not found", resourceType));
    }

    public ResourceNotFoundException(String resourceType, Long id) {
        super(String.format("resource of the type: %s, with ID: %d was not found", resourceType, id));
    }
}
