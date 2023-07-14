package com.soomin.projectboardfinal.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soomin.projectboardfinal.dto.req.ReqArticleDto;
import com.soomin.projectboardfinal.dto.res.ResArticleDto;
import com.soomin.projectboardfinal.entity.Article;
import com.soomin.projectboardfinal.entity.QArticle;
import com.soomin.projectboardfinal.entity.QArticleComment;
import jakarta.persistence.EntityManager;
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
    private final EntityManager entityManager;

    /**
     * 게시글 리스트 조회
     *
     * @param   pageable pageable
     * @return  조회 결과
     */
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

    /**
     * 게시글 단일 조회
     *
     * @param   articleId 게시글 고유번호
     * @return  조회 결과
     */
    public Article findArticle(long articleId) {

        return jpaQueryFactory.selectFrom   (article)
                                    .where  (article.id.eq(articleId))
                                    .fetchOne();
    }

    /**
     * 게시글 수정
     *
     * @param articleId 게시글 고유번호
     */
    public void updateArticle(ReqArticleDto reqArticleDto, long articleId) {

        jpaQueryFactory.update  (article)
                        .set    (article.title,             reqArticleDto.getTitle())
                        .set    (article.content,           reqArticleDto.getContent())
                        .set    (article.createdBy,         reqArticleDto.getCreatedBy())
                        .where  (article.id.eq(articleId))
                        .execute();
    }

    /**
     * 게시글 count
     *
     * @return 게시글 count
     */
    public Long getArticleCount() {

        QArticle qArticle = QArticle.article;

            return new JPAQueryFactory(entityManager)
                    .select(qArticle.id.count())
                    .from(qArticle)
                    .fetchOne();
    }
}
