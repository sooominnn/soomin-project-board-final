package com.soomin.projectboardfinal.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soomin.projectboardfinal.dto.req.ReqArticleCommentDto;
import com.soomin.projectboardfinal.dto.res.ResArticleCommentDto;
import com.soomin.projectboardfinal.entity.ArticleComment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.soomin.projectboardfinal.entity.QArticle.article;
import static com.soomin.projectboardfinal.entity.QArticleComment.articleComment;

/**
 * fileName     : ArticleCommentQueryRepository
 * author       : lia
 * date         : 2023/07/03
 * description  : 게시글 댓글 Query Repository
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/03       lia          최초 생성
 */
@Repository
@RequiredArgsConstructor
public class ArticleCommentQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    /**
     * 댓글 리스트 조회
     *
     * @param   articleId   게시글 고유번호
     * @param   pageable    pageable
     * @return  조회 결과
     */
    public List<ResArticleCommentDto> findArticleCommentList(long articleId, Pageable pageable) {

        return jpaQueryFactory.select(Projections.fields(ResArticleCommentDto.class
                                                ,articleComment.id          .as("id")
                                                ,articleComment.content     .as("content")
                                                ,articleComment.createdBy   .as("createdBy")))
                                    .from       (articleComment)
                                    .where      (article.id.eq(articleId))
                                    .offset     (pageable.getOffset())
                                    .limit      (pageable.getPageSize())
                                    .fetch();
    }

    /**
     * 댓글 단일 조회
     *
     * @param   articleId           게시글 고유번호
     * @param   articleCommentId    댓글 고유번호
     * @return  조회 결과
     */
    public ArticleComment findArticleComment(long articleId, long articleCommentId) {

        return jpaQueryFactory.selectFrom   (articleComment)
                                    .join   (articleComment.article,    article)
                                    .where  (article.id                 .eq(articleId)
                                            ,articleComment.id          .eq(articleCommentId))
                                    .fetchOne();
    }

    /**
     * 댓글 수정
     *
     * @param reqArticleCommentDto  댓글 정보
     * @param articleId             게시글 고유번호
     * @param articleCommentId      댓글 고유번호
     */
    public void updateArticleComment(ReqArticleCommentDto reqArticleCommentDto, long articleId, long articleCommentId) {

        jpaQueryFactory.update  (articleComment)
                        .set    (articleComment.content,        reqArticleCommentDto.getContent())
                        .set    (articleComment.createdBy,      reqArticleCommentDto.getCreatedBy())
                        .where  (articleComment.id              .eq(articleCommentId)
                                ,articleComment.article.id      .eq(articleId))
                        .execute();
    }
}
