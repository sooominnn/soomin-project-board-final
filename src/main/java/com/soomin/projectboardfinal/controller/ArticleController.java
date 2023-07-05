package com.soomin.projectboardfinal.controller;

import com.soomin.projectboardfinal.common.Response;
import com.soomin.projectboardfinal.common.StatusCode;
import com.soomin.projectboardfinal.dto.req.ReqArticleDto;
import com.soomin.projectboardfinal.dto.res.ResArticleDto;
import com.soomin.projectboardfinal.service.serviceinterface.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * fileName     : ArticleController
 * author       : lia
 * date         : 2023/07/03
 * description  : 게시글 Controller
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/03       lia          최초 생성
 */
@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    /**
     * 게시글 리스트 조회
     * @param   pageable    pageable
     * @return  게시글 리스트
     */
    @GetMapping(value = "/api/articles")
    public ResponseEntity<Response> getArticleList(Pageable pageable) {

        Page<ResArticleDto> resArticleDtoList = articleService.getArticleList(pageable);

        return Response.toResponseEntity(StatusCode.SEARCH_SUCCESS, resArticleDtoList);
    }

    /**
     * 게시글 단일 조회
     *
     * @param   articleId   게시글 고유번호
     * @return  조회 결과
     */
    @GetMapping(value = "/api/articles/{articleId}")
    public ResponseEntity<Response> getArticle(@PathVariable long articleId) {

        articleService.getArticle(articleId);

        return Response.toResponseEntity(StatusCode.SEARCH_SUCCESS);
    }

    /**
     * 게시글 생성
     *
     * @param   reqArticleDto   게시글 정보
     * @return 생성 결과
     */
    @PostMapping(value = "/api/articles")
    public ResponseEntity<Response> saveArticle(@RequestBody @Valid ReqArticleDto reqArticleDto) {

        articleService.saveArticle(reqArticleDto);

        return Response.toResponseEntity(StatusCode.REGISTER_SUCCESS);
    }

    /**
     * 게시글 수정
     *
     * @param   articleId       게시글 고유번호
     * @param   reqArticleDto   게시글 정보
     * @return  수정 결과
     */
    @PatchMapping(value = "/api/articles/{articleId}")
    public ResponseEntity<Response> updateArticle(@PathVariable long articleId, @RequestBody @Valid ReqArticleDto reqArticleDto) {

        articleService.updateArticle(reqArticleDto, articleId);

        return Response.toResponseEntity(StatusCode.UPDATE_SUCCESS);
    }

    /**
     * 게시글 삭제
     *
     * @param   articleId   게시글 고유번호
     * @return  삭제 결과
     */
    @DeleteMapping(value = "/api/articles/{articleId}")
    public ResponseEntity<Response> deleteArticle(@PathVariable long articleId) {

        articleService.deleteArticle(articleId);

        return Response.toResponseEntity(StatusCode.DELETE_SUCCESS);
    }
}
