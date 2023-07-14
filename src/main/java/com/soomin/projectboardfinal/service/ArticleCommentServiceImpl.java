package com.soomin.projectboardfinal.service;

import com.soomin.projectboardfinal.common.CustomException;
import com.soomin.projectboardfinal.common.StatusCode;
import com.soomin.projectboardfinal.dto.req.ReqArticleCommentDto;
import com.soomin.projectboardfinal.dto.res.ResArticleCommentDto;
import com.soomin.projectboardfinal.entity.ArticleComment;
import com.soomin.projectboardfinal.repository.ArticleCommentQueryRepository;
import com.soomin.projectboardfinal.service.serviceinterface.ArticleCommentService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    private final ArticleCommentQueryRepository articleCommentQueryRepository;

    /**
     * 댓글 리스트 조회
     *
     * @param   articleId 게시글 고유번호
     * @param   pageable  pageable
     * @return  조회 결과
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ResArticleCommentDto> getArticleCommentList(long articleId, Pageable pageable) {

        // 댓글 리스트 조회
        List<ResArticleCommentDto> resArticleCommentDtoList = articleCommentQueryRepository.findArticleCommentList(articleId, pageable);

//        // 댓글 count 조회
//        JPAQuery<Long> countQuery = articleCommentQueryRepository.getArticleCommentCount();
//
//        return PageableExecutionUtils.getPage(resArticleCommentDtoList, pageable, countQuery::fetchOne);

        // 댓글 count 조회
        Long count = articleCommentQueryRepository.getArticleCommentCount();

        return PageableExecutionUtils.getPage(resArticleCommentDtoList, pageable, () -> count);
    }

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

    /**
     * 댓글 삭제
     *
     * @param articleId        게시글 고유번호
     * @param articleCommentId 댓글 고유번호
     */
    @Override
    @Transactional
    public void deleteArticleComment(long articleId, long articleCommentId) {

        // 댓글 조회
        ArticleComment articleComment = articleCommentQueryRepository.findArticleComment(articleId, articleCommentId);
        if (articleComment == null) throw new CustomException(StatusCode.NOT_FOUND, "해당 댓글을 찾을 수 없습니다");

        // 댓글 삭제
        entityManager.remove(articleComment);
        entityManager.close();
    }
}
