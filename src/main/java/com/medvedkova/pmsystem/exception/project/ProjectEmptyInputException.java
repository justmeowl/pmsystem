package com.medvedkova.pmsystem.exception.project;

public class ProjectEmptyInputException extends ProjectException {

    private final String pagePath;

    public ProjectEmptyInputException(String pagePath, String message) {
        super(message);
        this.pagePath = pagePath;
    }

    public String getPagePath() {
        return pagePath;
    }
}
