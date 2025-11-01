package com.markelloww.projectmanagement.controller;

import com.markelloww.projectmanagement.model.User;
import com.markelloww.projectmanagement.repository.UserRepository;
import com.markelloww.projectmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProfileController {
    public static final String ERROR = "error";
    public static final String REDIRECT_PROFILE = "redirect:/profile";
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

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

        if (!hasValidationErrors(user, email, newPassword, confirmPassword, currentPassword, redirectAttributes)) {
            updateUser(user, email, firstname, lastname, newPassword);
            redirectAttributes.addFlashAttribute("success", "Профиль успешно обновлен");
        }

        return REDIRECT_PROFILE;
    }

    private void updateUser(User user, String email, String firstname,
                            String lastname, String newPassword) {
        user.setEmail(email);
        user.setFirstname(firstname);
        user.setLastname(lastname);

        if (newPassword != null && !newPassword.isEmpty()) {
            user.setPassword(passwordEncoder.encode(newPassword));
        }

        userRepository.save(user);
    }

    private boolean hasValidationErrors(User user, String email, String newPassword,
                                        String confirmPassword, String currentPassword,
                                        RedirectAttributes redirectAttributes) {
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            redirectAttributes.addFlashAttribute(ERROR, "Введен неверный пароль");
            return true;
        }

        if (!email.equalsIgnoreCase(user.getEmail()) &&
                userRepository.existsByEmail(email.toLowerCase())) {
            redirectAttributes.addFlashAttribute(ERROR, "Такой e-mail уже занят");
            return true;
        }

        if (newPassword != null && !newPassword.isEmpty()) {
            redirectAttributes.addFlashAttribute(ERROR, "Пароль не может быть пустым");
            return true;
        }

        if (newPassword != null && !newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute(ERROR, "Новый пароль и подтверждение не совпадают");
            return true;
        }

        return false;
    }

}
