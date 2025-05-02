package com.markelloww.projectmanagement.controller;

import com.markelloww.projectmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

/**
 * @Author: Markelloww
 */

@Controller
@RequiredArgsConstructor
public class ProfileController {
    private final UserService userService;

    @GetMapping("/profile")
    public String getProfilePage(Principal principal, Model model) {
        String email = principal.getName();
        model.addAttribute("firstname", userService.getFirstNameByEmail(email));
        return "profile";
    }
}
