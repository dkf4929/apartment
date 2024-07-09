package com.project.apartment.domain.user.member.dto;

import com.project.apartment.domain.enums.Gender;
import com.project.apartment.domain.user.member.Member;

public class MemberResponseDto {
    private String loginId;

    private String regNo;

    private String name;

    private String gender;

    private String address;

    private String birthDate;

    public static MemberResponseDto of(Member member, String regNo) {
        MemberResponseDto dto = new MemberResponseDto();

        dto.loginId = member.getLoginId();
        dto.regNo = regNo;
        dto.name = member.getName();
        dto.gender = member.getGender().name();
        dto.address = member.getAddress();
        dto.birthDate = member.getBirthDate();

        return dto;
    }
}
