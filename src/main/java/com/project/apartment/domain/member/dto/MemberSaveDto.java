package com.project.apartment.domain.member.dto;

import com.project.apartment.domain.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
public class MemberSaveDto {
    @NotBlank
    @Size(max = 20, message = "아이디는 최대 20글자 입니다.")
    private String loginId;

    @NotBlank
    @Size(min = 6, message = "비밀번호는 최소 6글자 이상이여야 합니다.")
    private String password;

    @NotBlank
    @Pattern(regexp = "^\\d{6}-\\d{7}$", message = "주민등록번호 형식이 올바르지 않습니다.")
    private String regNo;

    @NotBlank
    @Size(min = 2, max = 20, message = "이름은 2~20 글자 이내여야 합니다.")
    private String name;

    private Gender gender;

    @NotBlank
    private String address;

    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String birthDate;
}
