package com.soomin.projectboardfinal.dto.req;

import com.soomin.projectboardfinal.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * fileName     : ReqJoinDto
 * author       : lia
 * date         : 2023/07/07
 * description  : 회원가입 DTO
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/07       lia          최초 생성
 */
@Getter
public class ReqJoinDto {

    @NotBlank
    private String email;           // 이메일(아이디)
    @NotBlank
    private String password;        // 비밀번호
    @NotBlank
    private String passwordCheck;   // 비밀번호 확인
    @NotBlank
    private String nickname;        // 닉네임

    // 비밀번호 암호화
    public Member toEntity(String encoderPassword) {
        return Member.builder()
                            .email(this.email)
                            .password(encoderPassword)
                            .nickname(this.nickname)
                    .build();
    }
}
