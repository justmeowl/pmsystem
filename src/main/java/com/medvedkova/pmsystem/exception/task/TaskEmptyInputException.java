package com.medvedkova.pmsystem.exception.task;

public class TaskEmptyInputException extends TaskException {

    public TaskEmptyInputException(String pagePath, String message) {
        super(pagePath, message);
    }
}
