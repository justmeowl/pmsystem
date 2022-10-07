package com.medvedkova.pmsystem.exception.user;

public class UserNotFoundException extends UserException {

    public UserNotFoundException() {
        super("User Not Found Exception!");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
