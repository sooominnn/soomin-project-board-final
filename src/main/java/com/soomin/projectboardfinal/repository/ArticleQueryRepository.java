package com.soomin.projectboardfinal.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soomin.projectboardfinal.dto.res.ResArticleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.soomin.projectboardfinal.entity.QArticle.article;

/**
 * fileName     : ArticleQueryRepository
 * author       : lia
 * date         : 2023/07/03
 * description  : 게시글 Query Repository
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/03       lia          최초 생성
 */
@Repository
@RequiredArgsConstructor
public class ArticleQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<ResArticleDto> findArticleList(Pageable pageable) {

        return jpaQueryFactory.select(Projections.fields(ResArticleDto.class
                                                ,article.id         .as("id")
                                                ,article.title      .as("title")
                                                ,article.content    .as("content")
                                                ,article.createdBy  .as("createdBy")
                                                ,article.createdAt  .as("createdAt")))
                                    .from       (article)
                                    .offset     (pageable.getOffset())
                                    .limit      (pageable.getPageSize())
                                    .fetch();
    }
}
