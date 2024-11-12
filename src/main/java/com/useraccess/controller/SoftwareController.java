package com.useraccess.controller;

import org.springframework.ui.Model;
import com.useraccess.dto.SoftwareDto;
import com.useraccess.dto.UserRegistrationDto;
import com.useraccess.service.SoftwareService;
import com.useraccess.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/software")
@RequiredArgsConstructor
public class SoftwareController {
    private final SoftwareService softwareService;

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("software", new SoftwareDto());
        return "software/create";
    }

    @PostMapping("/create")
    public String createSoftware(@ModelAttribute SoftwareDto softwareDto) {
        softwareService.createSoftware(softwareDto);
        return "redirect:/software/list";
    }

    @GetMapping("/list")
    public String listSoftware(Model model) {
        model.addAttribute("softwareList", softwareService.getAllSoftware());
        return "software/list";
    }
}
