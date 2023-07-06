package com.soomin.projectboardfinal.service;

import com.soomin.projectboardfinal.common.CustomException;
import com.soomin.projectboardfinal.common.StatusCode;
import com.soomin.projectboardfinal.dto.req.ReqArticleCommentDto;
import com.soomin.projectboardfinal.entity.ArticleComment;
import com.soomin.projectboardfinal.repository.ArticleCommentQueryRepository;
import com.soomin.projectboardfinal.service.serviceinterface.ArticleCommentService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * fileName     : ArticleCommentServiceImpl
 * author       : lia
 * date         : 2023/07/03
 * description  : 게시글 댓글 ServiceImpl
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/03       lia          최초 생성
 */
@Service
@RequiredArgsConstructor
public class ArticleCommentServiceImpl implements ArticleCommentService {

    private final EntityManager entityManager;
    private final ArticleCommentService articleCommentService;
    private final ArticleCommentQueryRepository articleCommentQueryRepository;

    /**
     * 댓글 생성
     *
     * @param articleId            게시글 고유번호
     * @param reqArticleCommentDto 댓글 정보
     */
    @Override
    @Transactional
    public void saveArticleComment(long articleId, ReqArticleCommentDto reqArticleCommentDto) {

        // 댓글 생성
        ArticleComment articleComment = ArticleComment.builder()
                                                            .content(reqArticleCommentDto.getContent())
                                                            .createdBy(reqArticleCommentDto.getCreatedBy())
                                                            .createdAt(null)
                                                            .modifiedAt(null)
                                                            .modifiedBy(null)
                                                        .build();

        // 댓글 저장
        entityManager.persist(articleComment);
        entityManager.close();
    }

    /**
     * 댓글 수정
     *
     * @param articleId            게시글 고유번호
     * @param articleCommentId     댓글 고유번호
     * @param reqArticleCommentDto 댓글 정보
     */
    @Override
    @Transactional
    public void updateArticleComment(long articleId, long articleCommentId, ReqArticleCommentDto reqArticleCommentDto) {

        // 댓글 조회
        ArticleComment articleComment = articleCommentQueryRepository.findArticleComment(articleId, articleCommentId);
        if (articleComment == null) throw new CustomException(StatusCode.NOT_FOUND, "해당 댓글을 찾을 수 없습니다");

        // 댓글 수정
        articleCommentQueryRepository.updateArticleComment(reqArticleCommentDto, articleId, articleCommentId);
        entityManager.clear();
        entityManager.close();
        // TODO entityManager.flush(), entityManager.close()에 대해 더 알아보기
    }
}
