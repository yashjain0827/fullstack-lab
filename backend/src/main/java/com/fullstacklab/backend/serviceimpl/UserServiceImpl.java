package com.fullstacklab.backend.serviceimpl;

import com.fullstacklab.backend.entity.User;
import com.fullstacklab.backend.repository.UserRepository;
import com.fullstacklab.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User addUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException(
                    "User with email '" + user.getEmail() + "' already exists");
        }
        return userRepository.save(user);
    }
}