package com.medvedkova.pmsystem.exception.task;

public class TaskNumberNotUniqueException extends TaskException {

    public TaskNumberNotUniqueException(String pagePath) {
        super(pagePath, "Task Number already in use!");
    }

    public TaskNumberNotUniqueException(String pagePath, String message) {
        super(pagePath, message);
    }
}
