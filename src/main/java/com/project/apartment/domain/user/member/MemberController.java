package com.project.apartment.domain.user.member;

import com.project.apartment.domain.user.member.dto.MemberResponseDto;
import com.project.apartment.domain.user.member.dto.MemberSaveRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/member")
@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "회원 등록", description = "커뮤니티에 가입합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "가입 완료", content = @Content(
                    mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "유효하지 않은 입력값입니다.", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{ \"message\": \"올바른 입력값을 입력하세요.\" }"))),
            @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{ \"message\": \"서버 오류 발생.\" }"))),
    })
    @PostMapping
    public Long saveMember(@RequestBody @Valid MemberSaveRequestDto memberSaveRequestDto) {
        return memberService.saveMember(memberSaveRequestDto);
    }

    @Operation(summary = "회원 정보", description = "회원정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(
                    mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "유효하지 않은 입력값입니다.", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{ \"message\": \"올바른 입력값을 입력하세요.\" }"))),
            @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{ \"message\": \"서버 오류 발생.\" }"))),
    })
    @GetMapping
    public MemberResponseDto myInfo() {
        return memberService.findMember();
    }
}
