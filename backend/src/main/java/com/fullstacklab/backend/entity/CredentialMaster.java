package com.fullstacklab.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(        name = "credential_master")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"password"})
public class CredentialMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    @Column(nullable = false, unique = true, length = 100)
    private String email;


    @NotBlank(message = "Password is required")
    @Size(min = 60, max = 255, message = "Password hash length is invalid")
    @Column(nullable = false, length = 255)
    private String password;

    @NotNull(message = "userId is required")
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @NotNull(message = "userType is required")
    @ManyToOne
    @JoinColumn(name = "user_type_id")
    private UserType userType;

    @NotNull(message = "isActive must be specified")
    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;
}