package com.soomin.projectboardfinal.controller;

import com.soomin.projectboardfinal.common.Response;
import com.soomin.projectboardfinal.common.StatusCode;
import com.soomin.projectboardfinal.dto.req.ReqArticleCommentDto;
import com.soomin.projectboardfinal.service.serviceinterface.ArticleCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * fileName     : ArticleCommentController
 * author       : lia
 * date         : 2023/07/03
 * description  : 게시글 댓글 Controller
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/03       lia          최초 생성
 */
@RestController
@RequiredArgsConstructor
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;

    /**
     * 댓글 생성
     *
     * @param   articleId               게시글 고유번호
     * @param   reqArticleCommentDto    댓글 정보
     * @return  생성 결과
     */
    @PostMapping(value = "/api/articles/{articleId}/articleComments")
    public ResponseEntity<Response> saveArticleComment(@PathVariable long articleId, @RequestBody @Valid ReqArticleCommentDto reqArticleCommentDto) {

        articleCommentService.saveArticleComment(articleId, reqArticleCommentDto);

        return Response.toResponseEntity(StatusCode.REGISTER_SUCCESS);
    }
}
