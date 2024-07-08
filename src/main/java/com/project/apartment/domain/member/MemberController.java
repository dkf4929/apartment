package com.project.apartment.domain.member;

import com.project.apartment.domain.member.dto.MemberSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/member")
@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public String saveMember(@RequestBody MemberSaveDto memberSaveDto) {
        memberService.saveMember(memberSaveDto);

        return "회원가입이 완료되었습니다.";
    }
}
