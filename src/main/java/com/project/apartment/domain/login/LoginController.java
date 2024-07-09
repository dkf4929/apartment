package com.project.apartment.domain.login;

import com.project.apartment.domain.login.dto.LoginRequestDto;
import com.project.apartment.domain.login.dto.LoginResponseDto;
import com.project.apartment.domain.user.member.Member;
import com.project.apartment.global.dto.JwtTokenDto;
import com.project.apartment.global.jwt.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "로그인", description = "커뮤니티에 로그인 합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공", content = @Content(
                    mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "유효하지 않은 입력값입니다.", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{ \"message\": \"올바른 입력값을 입력하세요.\" }"))),
            @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{ \"message\": \"서버 오류 발생.\" }"))),
    })
    @PostMapping
    public LoginResponseDto login(HttpServletResponse response, @RequestBody @Valid LoginRequestDto loginRequestDto) {
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
