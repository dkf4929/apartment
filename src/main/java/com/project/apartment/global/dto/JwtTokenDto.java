package com.project.apartment.global.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class JwtTokenDto {
    private String grantType;
    private String accessToken;
    private Long accessTokenExpiresIn;
    private Long refreshTokenExpiresIn;
    private String refreshToken;
}
