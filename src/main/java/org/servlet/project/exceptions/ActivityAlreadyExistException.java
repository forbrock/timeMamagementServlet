package org.servlet.project.exceptions;

public class ActivityAlreadyExistException extends RuntimeException {
    public ActivityAlreadyExistException() {}

    public ActivityAlreadyExistException(String message) {
        super(message);
    }
}
