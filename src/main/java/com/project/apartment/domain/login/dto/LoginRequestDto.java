package com.project.apartment.domain.login.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequestDto {
    @NotBlank
    private String loginId;

    @NotBlank
    private String password;
}
