package com.asset.authorization_server.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String entityName, String entityField, Object entityFieldValue) {
        super(String.format("The resource of the type: '%s' with the field '%s' having value '%s' was not found",
                entityName, entityField, entityFieldValue));
    }
}
