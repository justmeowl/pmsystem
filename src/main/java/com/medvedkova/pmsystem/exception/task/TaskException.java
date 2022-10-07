package com.medvedkova.pmsystem.exception.task;

import com.medvedkova.pmsystem.exception.PmSystemException;

public class TaskException extends PmSystemException {

    private final String pagePath;

    public TaskException(String pagePath, String message) {
        super(message);
        this.pagePath = pagePath;
    }

    public String getPagePath() {
        return pagePath;
    }
}
