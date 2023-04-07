package com.softuni.shoestrade.model.dto;

import com.softuni.shoestrade.model.enums.Gender;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO {

    @NotNull
    @Size(min = 3, max = 18)
    private String username;

    @NotNull
    @Size(min = 3, max = 18)
    private String password;

    @Email
    @NotBlank
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    @NotNull
    @Size(min = 3, max = 26)
    private String fullName;

    @NotBlank
    private String description;

    @NotNull
    @Min(10)
    @Max(99)
    private int age;
}
