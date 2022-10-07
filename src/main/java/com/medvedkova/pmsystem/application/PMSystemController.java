package com.medvedkova.pmsystem.application;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

public interface PMSystemController {

    @ModelAttribute("error")
    default void addErrorMessageAttribute(String error, Model model) {
        model.addAttribute("errorMessage", error);
    }
}
