package com.markelloww.projectmanagement.controller;

import com.markelloww.projectmanagement.model.User;
import com.markelloww.projectmanagement.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @Author: Markelloww
 */

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            Model model) {
        if (error != null) {
            model.addAttribute("error", "Неверный e-mail или пароль");
        }
        return "login";
    }

    @GetMapping("/reg")
    public String regPage(@RequestParam(value = "msg", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Пользователь с таким e-mail уже зарегистрирован!");
        }
        return "reg";
    }

    @PostMapping("/reg")
    @Transactional
    public String regUser(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               RedirectAttributes redirectAttributes) {
        if (userRepository.findByEmail(username).isPresent()) {
            redirectAttributes.addAttribute("msg", "error");
            return "redirect:/reg";
        }

        User user = new User();
        user.setEmail(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

        return "redirect:/login";
    }
}
