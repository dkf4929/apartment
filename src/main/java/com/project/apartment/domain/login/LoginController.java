package com.project.apartment.domain.login;

import com.project.apartment.domain.login.dto.LoginRequestDto;
import com.project.apartment.domain.login.dto.LoginResponseDto;
import com.project.apartment.domain.member.Member;
import com.project.apartment.global.dto.JwtTokenDto;
import com.project.apartment.global.jwt.JwtTokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public LoginResponseDto login(HttpServletResponse response, @Valid LoginRequestDto loginRequestDto) {
        // 로그인 인증 처리
        Member loginMember = loginService.login(loginRequestDto);

        // 인증 객체 생성
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(loginMember, loginMember.getLoginId(), loginMember.getAuthorities());

        // accessToken 및 refreshToken 생성
        JwtTokenDto jwtTokenDto = jwtTokenProvider.generateToken(authentication);

        ResponseCookie cookie = ResponseCookie.from("refreshToken", jwtTokenDto.getRefreshToken())
                .sameSite("Strict")
                .path("/")
                .httpOnly(true)
                .secure(true)
                .build();

        String appendCookieExpires = cookie.toString() + "; Exipres=" + new Date(new Date().getTime() + jwtTokenDto.getRefreshTokenExpiresIn()).toString();
        response.addHeader(HttpHeaders.SET_COOKIE, appendCookieExpires);

        return LoginResponseDto.of(jwtTokenDto.getAccessToken(),
                jwtTokenDto.getAccessTokenExpiresIn(),
                jwtTokenDto.getRefreshTokenExpiresIn(),
                JwtTokenProvider.BEARER_TYPE);
    };
}
