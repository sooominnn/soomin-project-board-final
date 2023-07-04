package com.soomin.projectboardfinal.controller;

import com.soomin.projectboardfinal.common.Response;
import com.soomin.projectboardfinal.common.StatusCode;
import com.soomin.projectboardfinal.dto.res.ResArticleDto;
import com.soomin.projectboardfinal.service.serviceinterface.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
