package com.soomin.projectboardfinal.dto.req;

import lombok.Getter;

/**
 * fileName     : ReqArticleDto
 * author       : lia
 * date         : 2023/07/03
 * description  : 게시글 DTO
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/03       lia          최초 생성
 */
@Getter
public class ReqArticleDto {

    private String title;   // 제목
    private String content; // 내용
    private String createdBy;   // 작성자

    // TODO 등록일자 어떻게 할지
    // TODO 수정할 때 수정자, 수정일자 어떻게 할지
}
