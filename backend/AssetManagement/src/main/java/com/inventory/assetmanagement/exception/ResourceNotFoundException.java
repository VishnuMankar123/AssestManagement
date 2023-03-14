package com.inventory.assetmanagement.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String resourceType) {
        super(String.format("resource of the type: %s was not found", resourceType));
    }
}
