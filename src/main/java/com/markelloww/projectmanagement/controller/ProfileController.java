package com.markelloww.projectmanagement.controller;

import com.markelloww.projectmanagement.model.User;
import com.markelloww.projectmanagement.repository.UserRepository;
import com.markelloww.projectmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

/**
 * @Author: Markelloww
 */

@Controller
@RequiredArgsConstructor
public class ProfileController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration;

    @GetMapping("/profile")
    public String profilePage(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(
            @RequestParam String email,
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam(required = false) String newPassword,
            @RequestParam(required = false) String confirmPassword,
            @RequestParam String currentPassword,
            Principal principal,
            RedirectAttributes redirectAttributes) {
        User user = userService.getUserByEmail(principal.getName());
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            redirectAttributes.addFlashAttribute("error", "Введен неверный пароль");
            return "redirect:/profile";
        }
        if (!email.equalsIgnoreCase(user.getEmail())) {
            if (userRepository.existsByEmail(email.toLowerCase())) {
                redirectAttributes.addFlashAttribute("error", "Такой e-mail уже занят");
                return "redirect:/profile";
            }
            user.setEmail(email);
        }
        if (newPassword != null && !newPassword.isEmpty()) {
            if (!newPassword.equals(confirmPassword)) {
                redirectAttributes.addFlashAttribute("error", "Новый пароль и подтверждение не совпадают");
                return "redirect:/profile";
            }
            user.setPassword(passwordEncoder.encode(newPassword));
        }
        user.setFirstname(firstname);
        user.setLastname(lastname);
        userRepository.save(user);
        redirectAttributes.addFlashAttribute("success", "Профиль успешно обновлен");
        return "redirect:/profile";
    }
}
