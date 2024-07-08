package com.project.apartment.domain.member;

import com.project.apartment.domain.member.dto.MemberSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveMember(MemberSaveDto memberSaveDto) {
        Member member = Member.createMember(memberSaveDto.getLoginId(),
                passwordEncoder.encode(memberSaveDto.getPassword()),
                passwordEncoder.encode(memberSaveDto.getRegNo()),
                memberSaveDto.getName(),
                memberSaveDto.getGender(),
                memberSaveDto.getAddress(),
                memberSaveDto.getBirthDate(),
                List.of("ROLE_USER"));

        memberRepository.save(member);
    }
}
