package com.markelloww.projectmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

/**
 * @Author: Markelloww
 */

@Controller
public class ProfileController {
    @GetMapping("/profile")
    public String getProfilePage(Principal principal, Model model) {
        String email = principal.getName();

        model.addAttribute("userEmail", email);

        return "profile";
    }
}
