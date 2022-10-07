package com.medvedkova.pmsystem.exception.task;

public class TaskNotFoundException extends TaskException {

    public TaskNotFoundException(String pagePath) {
        super(pagePath, "Task Not Found!");
    }

    public TaskNotFoundException(String pagePath, String message) {
        super(pagePath, message);
    }
}
