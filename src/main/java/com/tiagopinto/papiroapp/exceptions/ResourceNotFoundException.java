package com.tiagopinto.papiroapp.exceptions;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String p0) {
        System.err.println(p0);
    }
}
