package com.fullstacklab.backend.dto;

import com.fullstacklab.backend.entity.Designation;
import com.fullstacklab.backend.entity.User;
import com.fullstacklab.backend.entity.UserType;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDTO {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Last name is required")
    @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[+]?[0-9]{10,15}$",
            message = "Phone number must be 10-15 digits, optionally starting with +")
    private String phoneNumber;

    @Size(max = 255, message = "Address must not exceed 255 characters")
    private String address;

    @Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be Male, Female, or Other")
    private String gender;

    @Size(max = 500, message = "Image URL must not exceed 500 characters")
    private String image;

    private Designation designation;

    @Size(max = 100, message = "Company name must not exceed 100 characters")
    private String companyName;

    @NotNull(message = "userType is required")
    private UserType userType;

    public User convertToEntity(UserDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setAddress(dto.getAddress());
        user.setGender(dto.getGender());
        user.setImage(dto.getImage());
        user.setDesignation(dto.getDesignation());
        user.setCompanyName(dto.getCompanyName());
        user.setUserType(dto.getUserType());
        user.setIsActive(true);
        return user;
    }

    public UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .gender(user.getGender())
                .image(user.getImage())
                .designation(user.getDesignation())
                .companyName(user.getCompanyName())
                .userType(user.getUserType())
                .build();
    }
}