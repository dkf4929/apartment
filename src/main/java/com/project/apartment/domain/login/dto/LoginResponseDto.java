package com.project.apartment.domain.login.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class LoginResponseDto {
    @Schema(description = "액세스 토큰")
    private String accessToken;

    @Schema(description = "토큰 만료시간")
    private Long expiresIn;

    @Schema(description = "refreshToken 만료시간")
    private Long refreshTokenExpiresIn;

    @Schema(description = "토큰 타입")
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
