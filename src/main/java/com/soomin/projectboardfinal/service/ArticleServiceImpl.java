package com.soomin.projectboardfinal.service;

import com.querydsl.jpa.impl.JPAQuery;
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
    private final ArticleService articleService;
    private final ArticleQueryRepository articleQueryRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<ResArticleDto> getArticleList(Pageable pageable) {

        // 게시글 조회
        List<ResArticleDto> resArticleDtoList = articleQueryRepository.findArticleList(pageable);

        // 게시글 count 조회
        JPAQuery<Long> countQuery = articleService.getArticleCount();

        return PageableExecutionUtils.getPage(resArticleDtoList, pageable, countQuery::fetchOne);
    }

    // TODO Service 와 연결해서 어떻게 해결할지 고민해보기
    @Override
    public JPAQuery<Long> getArticleCount() {
        return null;
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
}
