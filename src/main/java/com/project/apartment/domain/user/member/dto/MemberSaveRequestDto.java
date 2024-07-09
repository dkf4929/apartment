package com.project.apartment.domain.user.member.dto;

import com.project.apartment.domain.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class MemberSaveRequestDto {
    @NotBlank
    @Size(max = 20, message = "아이디는 최대 20글자 입니다.")
    @Schema(description = "로그인 ID", example = "GILDONG1")
    private String loginId;

    @NotBlank
    @Size(min = 6, message = "비밀번호는 최소 6글자 이상이여야 합니다.")
    @Schema(description = "비밀번호", example = "qwer1234")
    private String password;

    @NotBlank
    @Pattern(regexp = "^\\d{6}-\\d{7}$", message = "주민등록번호 형식이 올바르지 않습니다.")
    @Schema(description = "주민등록번호", example = "900101-1111111")
    private String regNo;

    @NotBlank
    @Size(min = 2, max = 20, message = "이름은 2~20 글자 이내여야 합니다.")
    @Schema(description = "사용자명", example = "홍길동")
    private String name;

    @Schema(description = "성별", example = "M")
    private Gender gender;

    @NotBlank
    @Schema(description = "주소", example = "서울 종로구 사직로 161")
    private String address;

    @NotBlank
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "생년월일 형식이 올바르지 않습니다. yyyy-MM-dd 형식이어야 합니다.")
    @Schema(description = "생년월일", example = "1990-01-01")
    private String birthDate;
}
