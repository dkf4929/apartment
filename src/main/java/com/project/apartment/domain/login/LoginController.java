package com.project.apartment.domain.login;

import com.project.apartment.domain.login.dto.LoginRequestDto;
import com.project.apartment.domain.login.dto.LoginResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;

//    @PostMapping
//    public LoginResponseDto login(HttpServletResponse response, @Valid LoginRequestDto requestDto) {
//
//    };
}
