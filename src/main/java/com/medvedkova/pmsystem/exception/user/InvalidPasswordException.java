package com.medvedkova.pmsystem.exception.user;

public class InvalidPasswordException extends UserException {

    public InvalidPasswordException(String message) {
        super(message);
    }
}
