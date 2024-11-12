package com.useraccess.controller;

import org.springframework.ui.Model;
import com.useraccess.dto.RequestDto;
import com.useraccess.dto.UserRegistrationDto;
import com.useraccess.entity.RequestStatus;
import com.useraccess.entity.User;
import com.useraccess.service.RequestService;
import com.useraccess.service.SoftwareService;
import com.useraccess.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/request")
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;
    private final SoftwareService softwareService;

    @GetMapping("/create")
    public String showRequestForm(Model model) {
        model.addAttribute("request", new RequestDto());
        model.addAttribute("softwareList", softwareService.getAllSoftware());
        return "request/create";
    }

    @PostMapping("/create")
    public String createRequest(
            @ModelAttribute RequestDto requestDto,
            @AuthenticationPrincipal User user
    ) {
        requestService.createRequest(requestDto, user.getId());
        return "redirect:/dashboard";
    }

    @GetMapping("/pending")
    public String listPendingRequests(Model model) {
        model.addAttribute("requests", requestService.getPendingRequests());
        return "request/list";
    }

    @PostMapping("/{id}/approve")
    public String approveRequest(@PathVariable Long id) {
        requestService.updateRequestStatus(id, RequestStatus.APPROVED);
        return "redirect:/request/pending";
    }

    @PostMapping("/{id}/reject")
    public String rejectRequest(@PathVariable Long id) {
        requestService.updateRequestStatus(id, RequestStatus.REJECTED);
        return "redirect:/request/pending";
    }
}