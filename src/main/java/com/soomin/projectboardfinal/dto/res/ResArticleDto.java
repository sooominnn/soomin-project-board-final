package com.soomin.projectboardfinal.dto.res;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * fileName     : ResArticleDto
 * author       : lia
 * date         : 2023/07/03
 * description  : 게시글 DTO
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/03       lia          최초 생성
 */
@Getter
public class ResArticleDto {

    private long            id;             // 게시글 고유번호
    private String          title;          // 제목
    private String          content;        // 내용
    private String          createdBy;      // 생성자
    private LocalDateTime   createdAt;      // 생성일시
}
