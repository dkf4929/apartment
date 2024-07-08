package com.project.apartment.domain.login;

import com.project.apartment.domain.login.dto.LoginRequestDto;
import com.project.apartment.domain.member.Member;
import com.project.apartment.domain.member.MemberRepository;
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

        Member findMember = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new MemberNotFoundException("등록되지 않은 사용자 입니다."));

        boolean passwordMatch
                = passwordEncoder.matches(findMember.getPassword(), password);

        if (!passwordMatch)
            throw new PasswordNotMatchException("비밀번호가 일치하지 않습니다.");

        return findMember;
    }
}
