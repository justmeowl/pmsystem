package com.medvedkova.pmsystem.user;

import com.medvedkova.pmsystem.application.PMSystemController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
final class UserController
        implements PMSystemController {

    private final UserDetailsService userDetailService;

    public UserController(UserDetailsService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @GetMapping()
    public String welcomePage() {
        return "index";
    }

    @GetMapping("/signup")
    public String signUpPage(Model model) {
        model.addAttribute("modalWindow", "sign-up");
        return "index";
    }

    @PostMapping("/signup")
    public String signUpUser(User user, String confirmPassword) {
        userDetailService.registerNewUser(user, confirmPassword);
        return "redirect:/signin";
    }

    @GetMapping("/signin")
    public String signInPage(Model model) {
        model.addAttribute("modalWindow", "sign-in");
        return "index";
    }

    @GetMapping("/logout")
    public String logOut() {
        return "redirect:/";
    }
}
