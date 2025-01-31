package com.blog.blogspot.exception;

import java.util.List;

public class ConflictException extends RuntimeException {

    private final List<String> errors;

    public ConflictException(List<String> errors) {
        super("Conflict occurred");
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
