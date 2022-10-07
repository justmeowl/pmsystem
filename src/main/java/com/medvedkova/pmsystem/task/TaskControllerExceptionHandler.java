package com.medvedkova.pmsystem.task;

import com.medvedkova.pmsystem.exception.task.TaskEmptyInputException;
import com.medvedkova.pmsystem.exception.task.TaskException;
import com.medvedkova.pmsystem.exception.task.TaskNotFoundException;
import com.medvedkova.pmsystem.exception.task.TaskNumberNotUniqueException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TaskControllerExceptionHandler {

    @ExceptionHandler({TaskNotFoundException.class,
            TaskEmptyInputException.class,
            TaskNumberNotUniqueException.class})
    public String handlerTaskException(TaskException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "redirect:/project/" + e.getPagePath();
    }
}
