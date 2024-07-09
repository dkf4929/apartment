package com.project.apartment.domain.user.member;

import com.project.apartment.domain.user.member.dto.MemberResponseDto;
import com.project.apartment.domain.user.member.dto.MemberSaveRequestDto;
import com.project.apartment.global.exception.CryptographyException;
import com.project.apartment.global.exception.MemberNotFoundException;
import com.project.apartment.global.utils.AESEncryptionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Long saveMember(MemberSaveRequestDto memberSaveRequestDto) {
        String encryptedRegNo = "";

        // 주민번호 암호화
        try {
            encryptedRegNo = AESEncryptionUtils.encrypt(memberSaveRequestDto.getRegNo());
        } catch (Exception e) {
            throw new CryptographyException("주민번호 암호화 중 오류 발생");
        }

        Member member = Member.createMember(memberSaveRequestDto.getLoginId(),
                passwordEncoder.encode(memberSaveRequestDto.getPassword()),
                passwordEncoder.encode(encryptedRegNo),
                memberSaveRequestDto.getName(),
                memberSaveRequestDto.getGender(),
                memberSaveRequestDto.getAddress(),
                memberSaveRequestDto.getBirthDate(),
                List.of("ROLE_USER"));

        // 회원 저장
        Member savedMember = memberRepository.save(member);

        return savedMember.getId();
    }

    public MemberResponseDto findMember() {
        // 인증 객체에서 로그인 정보를 꺼낸다.
        Optional<Member> loginMember =
                Optional.ofNullable((Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        // 없을 경우 ex처리
        if (loginMember.isEmpty())
            throw new AuthenticationCredentialsNotFoundException("로그인 후 진행하세요.");

        Long memberId = loginMember.get().getId();

        // 회원을 검색한다.
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("회원을 찾을 수 없습니다."));

        String decryptedRegNo = "";

        // 주민번호 복호화 처리
        try {
            decryptedRegNo = AESEncryptionUtils.decrypt(member.getRegNo());
        } catch (Exception e) {
            throw new CryptographyException("복호화 중 오류가 발생했습니다.");
        }

        return MemberResponseDto.of(member, decryptedRegNo);
    }
}
