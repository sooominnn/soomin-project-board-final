package com.soomin.projectboardfinal.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * fileName     : ReqLoginDto
 * author       : lia
 * date         : 2023/07/07
 * description  : 로그인 DTO
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/07       lia          최초 생성
 */
@Getter
public class ReqLoginDto {

    @NotBlank
    private String email;           // 이메일(로그인 아이디)
    @NotBlank
    private String password;        // 비밀번호
}
