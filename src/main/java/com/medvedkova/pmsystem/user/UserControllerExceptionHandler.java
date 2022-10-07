package com.medvedkova.pmsystem.user;

import com.medvedkova.pmsystem.exception.user.InvalidPasswordException;
import com.medvedkova.pmsystem.exception.user.InvalidUsernameException;
import com.medvedkova.pmsystem.exception.user.UserAlreadyExistException;
import com.medvedkova.pmsystem.exception.user.UserException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserControllerExceptionHandler {

    @ExceptionHandler(InvalidUsernameException.class)
    String handleSignUp(UserException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "redirect:/signin";
    }

    @ExceptionHandler({UserAlreadyExistException.class,
            UserAlreadyExistException.class,
            InvalidPasswordException.class})
    String handleSignIn(UserException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "redirect:/signup";
    }
}
