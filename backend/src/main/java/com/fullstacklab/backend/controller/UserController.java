package com.fullstacklab.backend.controller;

import com.fullstacklab.backend.dto.UserDTO;
import com.fullstacklab.backend.entity.User;
import com.fullstacklab.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/v1/user/add")
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userDTO) {
        User user = userService.addUser(userDTO.convertToEntity(userDTO));
        return new ResponseEntity<>(userDTO.convertToDTO(user), HttpStatus.CREATED);
    }
}