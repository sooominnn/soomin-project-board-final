package com.soomin.projectboardfinal.controller;

import com.soomin.projectboardfinal.common.Response;
import com.soomin.projectboardfinal.common.StatusCode;
import com.soomin.projectboardfinal.dto.req.ReqArticleCommentDto;
import com.soomin.projectboardfinal.dto.res.ResArticleCommentDto;
import com.soomin.projectboardfinal.service.serviceinterface.ArticleCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
     * 댓글 리스트 조회
     *
     * @param   articleId   게시글 고유번호
     * @param   pageable    pageable
     * @return  조회 결과
     */
    @GetMapping(value = "/api/articles/{articleId}/articleComments")
    public ResponseEntity<Response> getArticleCommentList(@PathVariable long articleId, Pageable pageable) {

        Page<ResArticleCommentDto> resArticleCommentDtoList = articleCommentService.getArticleCommentList(articleId, pageable);

        return Response.toResponseEntity(StatusCode.SEARCH_SUCCESS, resArticleCommentDtoList);
    }

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

    /**
     * 댓글 수정
     *
     * @param   articleId               게시글 고유번호
     * @param   articleCommentId        댓글 고유번호
     * @param   reqArticleCommentDto    댓글 정보
     * @return  수정 결과
     */
    @PatchMapping(value = "/api/articles/{articleId}/articleComments/{articleCommentId}")
    public ResponseEntity<Response> updateComment(@PathVariable long articleId, @PathVariable long articleCommentId, @RequestBody @Valid ReqArticleCommentDto reqArticleCommentDto) {

        articleCommentService.updateArticleComment(articleId, articleCommentId, reqArticleCommentDto);

        return Response.toResponseEntity(StatusCode.UPDATE_SUCCESS);
    }

    /**
     * 댓글 삭제
     *
     * @param   articleId           게시글 고유번호
     * @param   articleCommentId    댓글 고유번호
     * @return  삭제 결과
     */
    @DeleteMapping(value = "/api/articles/{articleId}/articleComments/{articleCommentId}")
    public ResponseEntity<Response> deleteArticleComment(@PathVariable long articleId, @PathVariable long articleCommentId) {

        articleCommentService.deleteArticleComment(articleId, articleCommentId);

        return Response.toResponseEntity(StatusCode.DELETE_SUCCESS);
    }
}
