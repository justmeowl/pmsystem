package com.medvedkova.pmsystem.exception.project;

public class ProjectNotFoundException extends ProjectException {

    public ProjectNotFoundException() {
        super("Project Not Found Exception!");
    }

    public ProjectNotFoundException(String message) {
        super(message);
    }
}
