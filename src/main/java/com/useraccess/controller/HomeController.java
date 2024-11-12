package com.useraccess.controller;

import com.useraccess.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal User user, Model model) {
        // Add user information to the model
        model.addAttribute("username", user.getUsername());
        model.addAttribute("role", user.getRole().name());

        // Route to different dashboards based on role
        switch (user.getRole()) {
            case ADMIN:
                return "dashboard/admin";
            case MANAGER:
                return "dashboard/manager";
            case EMPLOYEE:
                return "dashboard/employee";
            default:
                return "redirect:/login";
        }
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "error/403";
    }
}