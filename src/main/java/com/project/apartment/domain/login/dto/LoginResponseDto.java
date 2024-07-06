package com.project.apartment.domain.login.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {
    private String accessToken;

    private Long expiresIn;

    private Long refreshTokenExpiresIn;

    private String tokenType;

    public static LoginResponseDto of(String accessToken, long expiresIn, long refreshTokenExpiresIn, String tokenType) {
        LoginResponseDto loginResponseDto = new LoginResponseDto();

        loginResponseDto.accessToken = accessToken;
        loginResponseDto.expiresIn = expiresIn;
        loginResponseDto.refreshTokenExpiresIn = refreshTokenExpiresIn;
        loginResponseDto.tokenType = tokenType;

        return loginResponseDto;
    }
}
