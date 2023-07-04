package com.soomin.projectboardfinal.service;

import com.querydsl.jpa.impl.JPAQuery;
import com.soomin.projectboardfinal.dto.res.ResArticleDto;
import com.soomin.projectboardfinal.repository.ArticleQueryRepository;
import com.soomin.projectboardfinal.service.serviceinterface.ArticleService;
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

    @Override
    public JPAQuery<Long> getArticleCount() {
        return null;
    }
}
