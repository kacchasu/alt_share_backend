package com.queerxdisasster.altshare.security;

import com.queerxdisasster.altshare.model.User;
import com.queerxdisasster.altshare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User register(User user) {
        if (user.getPassword() == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
