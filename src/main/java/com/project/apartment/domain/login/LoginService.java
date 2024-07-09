package com.project.apartment.domain.login;

import com.project.apartment.domain.login.dto.LoginRequestDto;
import com.project.apartment.domain.user.member.Member;
import com.project.apartment.domain.user.member.MemberRepository;
import com.project.apartment.global.exception.MemberNotFoundException;
import com.project.apartment.global.exception.PasswordNotMatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member login(LoginRequestDto loginRequestDto) {
        String loginId = loginRequestDto.getLoginId();
        String password = loginRequestDto.getPassword();

        // 회원 검색
        Member findMember = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new MemberNotFoundException("등록되지 않은 사용자 입니다."));

        // 패스워드 일치여부 검사
        boolean passwordMatch
                = passwordEncoder.matches(password, findMember.getPassword());

        if (!passwordMatch)
            throw new PasswordNotMatchException("비밀번호가 일치하지 않습니다.");

        return findMember;
    }
}
