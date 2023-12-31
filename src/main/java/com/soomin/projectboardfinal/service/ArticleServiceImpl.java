package com.soomin.projectboardfinal.service;

import com.querydsl.jpa.impl.JPAQuery;
import com.soomin.projectboardfinal.common.CustomException;
import com.soomin.projectboardfinal.common.StatusCode;
import com.soomin.projectboardfinal.dto.req.ReqArticleDto;
import com.soomin.projectboardfinal.dto.res.ResArticleDto;
import com.soomin.projectboardfinal.entity.Article;
import com.soomin.projectboardfinal.repository.ArticleQueryRepository;
import com.soomin.projectboardfinal.service.serviceinterface.ArticleService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * fileName     : ArticleServiceImpl
 * author       : lia
 * date         : 2023/07/03
 * description  : 게시글 ServiceImpl
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/03       lia          최초 생성
 */
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final EntityManager entityManager;
    private final ArticleQueryRepository articleQueryRepository;

    /**
     * 게시글 리스트 조회
     *
     * @param   pageable pageable
     * @return  조회 결과
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ResArticleDto> getArticleList(Pageable pageable) {

        // 게시글 조회
        List<ResArticleDto> resArticleDtoList = articleQueryRepository.findArticleList(pageable);

//        // 게시글 count 조회
//        JPAQuery<Long> countQuery = articleQueryRepository.getArticleCount();
//
//        return PageableExecutionUtils.getPage(resArticleDtoList, pageable, countQuery::fetchOne);

        // 게시글 count 조회
        Long count = articleQueryRepository.getArticleCount();

        return PageableExecutionUtils.getPage(resArticleDtoList, pageable, () -> count);
    }

    /**
     * 게시글 단일 조회
     *
     * @param articleId 게시글 고유번호
     */
    @Override
    @Transactional(readOnly = true)
    public void getArticle(long articleId) {

        // 게시글 조회
        Article article = articleQueryRepository.findArticle(articleId);
        if (article == null) throw new CustomException(StatusCode.NOT_FOUND, "해당 게시글이 없습니다.");
    }

    /**
     * 게시글 생성
     *
     * @param reqArticleDto 게시글 정보
     */
    @Override
    @Transactional
    public void saveArticle(ReqArticleDto reqArticleDto) {

        // 게시글 생성
        Article article = Article.builder()
                                        .title(reqArticleDto.getTitle())
                                        .content(reqArticleDto.getContent())
                                        .createdAt(null)
                                        .createdBy(reqArticleDto.getCreatedBy())
                                        .modifiedAt(null)
                                        .modifiedBy(null)
                                .build();

        // 게시글 저장
        entityManager.persist(article);
        entityManager.close();
    }

    /**
     * 게시글 수정
     *
     * @param reqArticleDto 게시글 정보
     * @param articleId     게시글 고유번호
     */
    @Override
    @Transactional
    public void updateArticle(ReqArticleDto reqArticleDto, long articleId) {

        // 게시글 조회
        Article article = articleQueryRepository.findArticle(articleId);
        if (article == null) throw new CustomException(StatusCode.NOT_FOUND, "해당 게시글을 찾을 수 없습니다.");

        // 게시글 수정
        articleQueryRepository.updateArticle(reqArticleDto, articleId);
        entityManager.clear();
        entityManager.close();
        // TODO entityManager.flush(), entityManager.close()에 대해 더 알아보기

    }

    /**
     * 게시글 삭제
     *
     * @param articleId 게시글 고유번호
     */
    @Override
    @Transactional
    public void deleteArticle(long articleId) {

        // 게시글 조회
        Article article = articleQueryRepository.findArticle(articleId);
        if (article == null) throw new CustomException(StatusCode.NOT_FOUND, "해당 게시글이 없습니다.");

        // 게시글 삭제
        entityManager.remove(article);
        entityManager.close();
    }
}
