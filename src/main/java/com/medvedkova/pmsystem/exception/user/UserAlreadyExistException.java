package com.medvedkova.pmsystem.exception.user;

public class UserAlreadyExistException extends UserException {

    public UserAlreadyExistException() {
        super("User already Exist!");
    }

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
