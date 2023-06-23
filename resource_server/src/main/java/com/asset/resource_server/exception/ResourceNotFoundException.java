package com.asset.resource_server.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceType,
                                     String resourceProperty,
                                     Object resourcePropertyValue) {
        super(String.format("Resource of the type '%s' with the field '%s', having the value '%s' was not found!!",
                resourceType, resourceProperty, resourcePropertyValue));
    }
}
