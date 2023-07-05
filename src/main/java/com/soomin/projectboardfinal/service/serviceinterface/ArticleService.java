package com.soomin.projectboardfinal.service.serviceinterface;

import com.querydsl.jpa.impl.JPAQuery;
import com.soomin.projectboardfinal.dto.req.ReqArticleDto;
import com.soomin.projectboardfinal.dto.res.ResArticleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * fileName     : ArticleService
 * author       : lia
 * date         : 2023/07/03
 * description  : 게시글 Service
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/03       lia          최초 생성
 */
public interface ArticleService {

    /**
     * 게시글 리스트 조회
     *
     * @param   pageable    pageable
     * @return  게시글 리스트
     */
    Page<ResArticleDto> getArticleList(Pageable pageable);

    /**
     * 게시글 단일 조회
     *
     * @param articleId 게시글 고유번호
     */
    void getArticle(long articleId);

    /**
     * 게시글 Count 조회
     *
     * @return 게시글 count
     */
    JPAQuery<Long> getArticleCount();

    /**
     * 게시글 생성
     * @param reqArticleDto 게시글 정보
     */
    void saveArticle(ReqArticleDto reqArticleDto);

    /**
     * 게시글 수정
     *
     * @param reqArticleDto 게시글 정보
     * @param articleId     게시글 고유번호
     */
    void updateArticle(ReqArticleDto reqArticleDto, long articleId);

    /**
     * 게시글 삭제
     *
     * @param articleId 게시글 고유번호
     */
    void deleteArticle(long articleId);
}
