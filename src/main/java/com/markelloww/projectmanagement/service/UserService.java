package com.markelloww.projectmanagement.service;

import com.markelloww.projectmanagement.model.User;
import com.markelloww.projectmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Author: Markelloww
 */

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String getFirstNameByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(User::getFirstname)
                .orElse("");
    }
}
