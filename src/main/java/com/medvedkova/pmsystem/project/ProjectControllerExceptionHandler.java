package com.medvedkova.pmsystem.project;

import com.medvedkova.pmsystem.exception.project.ProjectEmptyInputException;
import com.medvedkova.pmsystem.exception.project.ProjectNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProjectControllerExceptionHandler {

    @ExceptionHandler(ProjectNotFoundException.class)
    public String handleProjectNotFoundException(ProjectNotFoundException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "redirect:/project";
    }

    @ExceptionHandler(ProjectEmptyInputException.class)
    public String handleProjectEmptyInputException(ProjectEmptyInputException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "redirect:/project/" + e.getPagePath();
    }
}
